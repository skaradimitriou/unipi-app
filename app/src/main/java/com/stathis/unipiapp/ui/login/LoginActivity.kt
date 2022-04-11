package com.stathis.unipiapp.ui.login

import android.app.AlertDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityLoginBinding
import com.stathis.unipiapp.databinding.ErrorOccuredLayoutBinding
import com.stathis.unipiapp.di.gson.DaggerGsonComponent
import com.stathis.unipiapp.models.Result
import com.stathis.unipiapp.ui.dashboard.DashboardActivity
import com.stathis.unipiapp.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : UnipiActivity<ActivityLoginBinding>(R.layout.activity_login) {

    @Inject
    lateinit var gson: Gson

    private lateinit var viewModel: LoginViewModel

    override fun init() {
        supportActionBar?.hide()

        DaggerGsonComponent.create().inject(this)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.isCtaEnabled = true
    }

    override fun startOps() {
        binding.loginBtn.setOnClickListener {
            val username = binding.emailInputField.text.toString()
            val password = binding.passInputField.text.toString()

            binding.isLoading = true
            binding.isCtaEnabled = false

            if (binding.loginRememberMeSwitch.isChecked) {
                getSharedPreferences(LOGIN, MODE_PRIVATE).save {
                    putString(USERNAME,username)
                    putString(PASSWORD,password)
                }
            }

            viewModel.validateUser(username, password)
        }

        binding.loginAsGuest.setOnClickListener {
            viewModel.loginGuestUser()
        }

        viewModel.data.observe(this) { result ->
            when(result){
                is Result.Success -> {
                    result.data?.let { user ->
                        startActivity(Intent(this, DashboardActivity::class.java).also {
                            it.putExtra(USER, gson.toJson(user))
                        })
                        finish()
                    }
                }
                is Result.Error -> {
                    binding.isCtaEnabled = true
                    binding.isLoading = false

                    showErrorMessage()
                }
            }
        }
    }

    override fun stopOps() {
        viewModel.data.removeObservers(this)
    }

    private fun showErrorMessage() {
        val binding = ErrorOccuredLayoutBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this, R.style.error_dialog).also {
            it.setView(binding.root)
            binding.errorMessage.text = getString(R.string.error_login_msg)
        }.show()

        clearLoginInput()

        lifecycleScope.launch {
            delay(2000)
            builder.hide()
        }
    }

    private fun clearLoginInput() {
        binding.emailInputField.clearInput()
        binding.passInputField.clearInput()
    }
}