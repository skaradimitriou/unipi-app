package com.stathis.unipiapp.ui.dashboard

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityDashboardBinding

class DashboardActivity : UnipiActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {

    private lateinit var navController : NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun startOps() {
        supportActionBar?.hide()
        binding.bottomNavigationMenu.setupWithNavController(navController)
    }

    override fun stopOps() {}

    override fun onBackPressed() {
        // back button disabled on purpose for this screen
    }
}