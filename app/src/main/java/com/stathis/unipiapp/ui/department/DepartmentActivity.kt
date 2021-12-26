package com.stathis.unipiapp.ui.department


import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityDepartmentBinding

class DepartmentActivity : UnipiActivity<ActivityDepartmentBinding>(R.layout.activity_department) {

    private lateinit var viewModel : DepartmentViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.department)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun stopOps() {
        //
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}