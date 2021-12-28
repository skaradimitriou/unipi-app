package com.stathis.unipiapp.ui.intro

import android.content.Intent
import com.bumptech.glide.Glide
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityMainBinding
import com.stathis.unipiapp.ui.dashboard.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : UnipiActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {
        binding.unipiLogo.setImageResource(R.drawable.ic_unipi_logo_svg)
        binding.unipiTitle.text = resources.getString(R.string.unipi_main_title)
        supportActionBar?.hide()
    }

    override fun startOps() {
        CoroutineScope(Dispatchers.Main).launch {
            startActivity(Intent(this@MainActivity,DashboardActivity::class.java))
            delay(5000)
        }
    }

    override fun stopOps() {}
}