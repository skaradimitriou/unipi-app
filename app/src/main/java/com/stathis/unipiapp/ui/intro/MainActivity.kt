package com.stathis.unipiapp.ui.intro

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityMainBinding
import com.stathis.unipiapp.ui.dashboard.DashboardActivity
import com.stathis.unipiapp.ui.login.LoginActivity
import com.stathis.unipiapp.util.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : UnipiActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var viewModel: MainViewModel

    override fun init() {
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun startOps() {
        val editor = getSharedPreferences(LOGIN, MODE_PRIVATE)
        val username = editor.getString(USERNAME, GUEST) ?: ""
        val password = editor.getString(PASSWORD, GUEST) ?: ""

        if (username == GUEST && password == GUEST) {
            lifecycleScope.launch {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
                delay(5000)
            }
        } else {
            viewModel.validateUser(username, password)
        }

        viewModel.data.observe(this) { response ->
            when (response) {
                is com.stathis.unipiapp.models.Result.Success -> {
                    response.data?.let { user ->
                        startActivity(Intent(this, DashboardActivity::class.java).also {
                            val json = Gson().toJson(user)
                            it.putExtra(USER, json)
                        })
                        finish()
                    }
                }

                is com.stathis.unipiapp.models.Result.Error -> {
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun stopOps() {
        viewModel.data.removeObservers(this)
    }
}