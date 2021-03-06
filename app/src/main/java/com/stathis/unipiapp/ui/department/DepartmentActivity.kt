package com.stathis.unipiapp.ui.department


import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.DepartmentCallback
import com.stathis.unipiapp.databinding.ActivityDepartmentBinding
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.ui.contact.ContactActivity
import com.stathis.unipiapp.ui.department.model.Programme

class DepartmentActivity : UnipiActivity<ActivityDepartmentBinding>(R.layout.activity_department) {

    private lateinit var viewModel : DepartmentViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.department)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel

        binding.deptFabBtn.setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }

        observe()
    }

    override fun stopOps() {
        viewModel.release(this)
    }

    private fun observe(){
        viewModel.observeData(this,object : DepartmentCallback {
            override fun openCarouselItem(model: CarouselItem) = when(model.title){
                getString(R.string.dept_research) -> openUrl(model.url)
                getString(R.string.dept_events) -> openUrl(model.url)
                getString(R.string.dept_contact) -> startActivity(Intent(this@DepartmentActivity, ContactActivity::class.java))
                else -> Unit
            }
            override fun openProgramme(model: Programme) {
                startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(model.url) })
            }
        })

        viewModel.error.observe(this) {
            when(it){
                true -> Snackbar.make(binding.deptScreenParent,getString(R.string.snackbar_error),Snackbar.LENGTH_LONG).show()
                false -> Unit
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    private fun openUrl(url : String){
        startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(url) })
    }
}