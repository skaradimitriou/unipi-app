package com.stathis.unipiapp.ui.about

import android.view.MenuItem
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityAboutBinding

class AboutActivity : UnipiActivity<ActivityAboutBinding>(R.layout.activity_about) {

    override fun init() {
        supportActionBar?.title = resources.getString(R.string.about_app_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        binding.aboutText = resources.getString(R.string.about_app_text)
        binding.disclamer = resources.getString(R.string.disclamer)
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}