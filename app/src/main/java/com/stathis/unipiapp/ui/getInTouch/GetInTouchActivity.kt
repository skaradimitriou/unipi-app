package com.stathis.unipiapp.ui.getInTouch

import android.view.MenuItem
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityGetInTouchBinding

class GetInTouchActivity : UnipiActivity<ActivityGetInTouchBinding>(R.layout.activity_get_in_touch) {


    override fun init() {
        supportActionBar?.title = "Επικοινωνία"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        binding.disclamer = resources.getString(R.string.disclamer)}

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}