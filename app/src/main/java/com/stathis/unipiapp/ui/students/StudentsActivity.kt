package com.stathis.unipiapp.ui.students

import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.StudentsCallback
import com.stathis.unipiapp.databinding.ActivityStudentsBinding
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.UnipiService

class StudentsActivity : UnipiActivity<ActivityStudentsBinding>(R.layout.activity_students) {

    private lateinit var viewModel : StudentsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.students)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.observe(this, object : StudentsCallback {
            override fun openCarouselItem(model: CarouselItem) {
                //
            }

            override fun openServices(model: UnipiService) {
                //
            }
        })
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