package com.stathis.unipiapp.ui.students

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.StudentsCallback
import com.stathis.unipiapp.databinding.ActivityStudentsBinding
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.ui.contact.ContactActivity
import com.stathis.unipiapp.util.BASE_URL

class StudentsActivity : UnipiActivity<ActivityStudentsBinding>(R.layout.activity_students) {

    private lateinit var viewModel : StudentsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.students)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel

        observe()
    }

    private fun observe() {
        viewModel.observe(this, object : StudentsCallback {
            override fun openCarouselItem(model: CarouselItem) = openItem(model)
            override fun openServices(model: UnipiService) = loadUrl(model.url)
        })

        viewModel.error.observe(this, Observer {
            when(it){
                true -> Snackbar.make(binding.studentsScreenParent,getString(R.string.snackbar_error), Snackbar.LENGTH_LONG).show()
                false -> Unit
            }
        })
    }

    private fun loadUrl(url : String) {
        startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(url) })
    }

    override fun stopOps() = viewModel.release(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    private fun openItem(item : CarouselItem) = when(item.position){
        1 -> loadUrl(item.url)
        2 -> loadUrl(item.url)
        3 -> loadUrl(item.url)
        else -> Unit
    }
}