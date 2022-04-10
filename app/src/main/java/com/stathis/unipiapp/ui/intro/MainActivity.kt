package com.stathis.unipiapp.ui.intro

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityMainBinding
import com.stathis.unipiapp.ui.dashboard.DashboardActivity
import com.stathis.unipiapp.ui.login.LoginActivity
import com.stathis.unipiapp.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

        viewModel.data.observe(this) { user ->
            user?.let {
                startActivity(Intent(this, DashboardActivity::class.java).also {
                    it.putExtra(USER, user)
                })
                finish()
            }
        }

        viewModel.error.observe(this) { errorOccured ->
            if (errorOccured) {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun stopOps() {
        viewModel.data.removeObservers(this)
        viewModel.error.removeObservers(this)
    }
}