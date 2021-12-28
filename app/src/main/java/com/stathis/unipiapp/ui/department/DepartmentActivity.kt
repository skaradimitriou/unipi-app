package com.stathis.unipiapp.ui.department


import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.DepartmentCallback
import com.stathis.unipiapp.databinding.ActivityDepartmentBinding
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.ui.contact.ContactActivity
import com.stathis.unipiapp.ui.department.model.Programme
import com.stathis.unipiapp.ui.webview.WebviewActivity

class DepartmentActivity : UnipiActivity<ActivityDepartmentBinding>(R.layout.activity_department) {

    private lateinit var viewModel : DepartmentViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.department)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.setVariable(BR.viewModel,viewModel)

        viewModel.observeData(this,object : DepartmentCallback {
            override fun openCarouselItem(model: CarouselItem) {
                //
            }

            override fun openProgramme(model: Programme) = openUrl(model.url)
        })

        binding.deptFabBtn.setOnClickListener {
            startActivity(Intent(this,ContactActivity::class.java))
        }
    }

    override fun stopOps() {
        viewModel.release(this)
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