package com.stathis.unipiapp.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityLoginBinding
import com.stathis.unipiapp.ui.dashboard.DashboardActivity
import com.stathis.unipiapp.util.LOGIN
import com.stathis.unipiapp.util.PASSWORD
import com.stathis.unipiapp.util.USER
import com.stathis.unipiapp.util.USERNAME

class LoginActivity : UnipiActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var viewModel: LoginViewModel

    override fun init() {
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.isCtaEnabled = true
    }

    override fun startOps() {
        binding.loginBtn.setOnClickListener {
            val username = binding.emailInputField.text.toString()
            val password = binding.passInputField.text.toString()

            binding.isCtaEnabled = false

            if (binding.loginRememberMeSwitch.isChecked) {
                val editor = getSharedPreferences(LOGIN, MODE_PRIVATE).edit()
                editor.putString(USERNAME, username)
                editor.putString(PASSWORD, password)
                editor.apply()
            }

            viewModel.validateUser(username, password)
        }

        binding.loginAsGuest.setOnClickListener {
            viewModel.loginGuestUser()
        }

        viewModel.data.observe(this) { user ->
            user?.let {
                startActivity(Intent(this, DashboardActivity::class.java).also {
                    it.putExtra(USER, user)
                })
                finish()
            }
        }

        viewModel.error.observe(this) {
            //FIXME: Implement error functionality
        }
    }

    override fun stopOps() {
        viewModel.data.removeObservers(this)
        viewModel.error.removeObservers(this)
    }
}