package com.stathis.unipiapp.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityLoginBinding
import com.stathis.unipiapp.ui.dashboard.DashboardActivity

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
            viewModel.validateUser(username, password)
        }

        viewModel.isLoggedIn.observe(this) {
            when (it) {
                true -> startActivity(Intent(this, DashboardActivity::class.java))
                false -> {} //error login attempt. Handle case with something
            }
        }
    }

    override fun stopOps() {
        viewModel.isLoggedIn.removeObservers(this)
    }
}