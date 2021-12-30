package com.stathis.unipiapp.ui.students

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.StudentsCallback
import com.stathis.unipiapp.databinding.ActivityStudentsBinding
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.util.BASE_URL

class StudentsActivity : UnipiActivity<ActivityStudentsBinding>(R.layout.activity_students) {

    private lateinit var viewModel : StudentsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.students)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.studentsScreenRecycler.adapter = viewModel.adapter

        viewModel.observe(this, object : StudentsCallback {
            override fun openCarouselItem(model: CarouselItem) {
                //
            }

            override fun openServices(model: UnipiService) = loadUrl(model.url)
        })
    }

    private fun loadUrl(url : String) {
        startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(url) })
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
}