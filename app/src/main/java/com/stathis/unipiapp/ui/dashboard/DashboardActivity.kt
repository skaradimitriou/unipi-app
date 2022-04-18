package com.stathis.unipiapp.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityDashboardBinding
import com.stathis.unipiapp.databinding.LeaveAppBottomSheetBinding
import com.stathis.unipiapp.di.gson.DaggerGsonComponent
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.ui.about.AboutActivity
import com.stathis.unipiapp.ui.announcements.AnnouncementsActivity
import com.stathis.unipiapp.ui.contact.ContactActivity
import com.stathis.unipiapp.ui.department.DepartmentActivity
import com.stathis.unipiapp.ui.getInTouch.GetInTouchActivity
import com.stathis.unipiapp.ui.login.LoginActivity
import com.stathis.unipiapp.ui.professors.ProfessorsActivity
import com.stathis.unipiapp.ui.students.StudentsActivity
import com.stathis.unipiapp.util.GUEST
import com.stathis.unipiapp.util.LOGIN
import com.stathis.unipiapp.util.USER
import com.stathis.unipiapp.util.USERNAME
import timber.log.Timber
import javax.inject.Inject

class DashboardActivity : UnipiActivity<ActivityDashboardBinding>(R.layout.activity_dashboard),
    NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var gson: Gson

    private lateinit var navController: NavController
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var viewModel: DashboardViewModel

    override fun init() {
        DaggerGsonComponent.create().inject(this)

        navController = findNavController(R.id.nav_host_fragment)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
    }

    override fun startOps() {
        val data = intent?.getStringExtra(USER)
        data?.let {
            val model = gson.fromJson(it, StudentsResponseDto::class.java)
            Timber.d("USER => $data")

            viewModel.setActiveUser(model)
        }

        binding.bottomNavigationMenu.setupWithNavController(navController)
        binding.drawerMenu.setNavigationItemSelectedListener(this)
    }

    override fun stopOps() {}

    override fun onBackPressed() {
        val binding = LeaveAppBottomSheetBinding.inflate(LayoutInflater.from(this))
        val dialog = BottomSheetDialog(this).also {
            it.setContentView(binding.root)
        }
        dialog.show()

        binding.bottomSheetYesBtn.setOnClickListener { finish() }
        binding.bottomSheetCancelBtn.setOnClickListener { dialog.hide() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (binding.drawerLayout.isOpen) {
            true -> binding.drawerLayout.closeDrawer(GravityCompat.START)
            false -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.announcements -> {
                startActivity(Intent(this, AnnouncementsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.department -> {
                startActivity(Intent(this, DepartmentActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.students -> {
                startActivity(Intent(this, StudentsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.professors -> {
                startActivity(Intent(this, ProfessorsActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.contact -> {
                startActivity(Intent(this, ContactActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.about_app_title -> {
                startActivity(Intent(this, AboutActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.get_in_touch -> {
                startActivity(Intent(this, GetInTouchActivity::class.java))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }

            R.id.logout -> {
                getSharedPreferences(LOGIN, MODE_PRIVATE).edit().also {
                    it.putString(USERNAME, GUEST)
                    it.putString(USERNAME, GUEST)
                }.apply()

                startActivity(Intent(this, LoginActivity::class.java).also {
                    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                })
            }
        }
        return true
    }
}