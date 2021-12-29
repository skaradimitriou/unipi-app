package com.stathis.unipiapp.ui.syllabus

import android.content.Intent
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.SemesterCallback
import com.stathis.unipiapp.databinding.ActivitySyllabusBinding
import com.stathis.unipiapp.models.Semester
import com.stathis.unipiapp.ui.lessons.LessonsActivity

class SyllabusActivity : UnipiActivity<ActivitySyllabusBinding>(R.layout.activity_syllabus) {

    private lateinit var viewModel : SyllabusViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(SyllabusViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.syllabus)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.setVariable(BR.adapter, viewModel.adapter)

        viewModel.observe(this, object : SemesterCallback {
            override fun onSemesterTap(model: Semester) {
                startActivity(Intent(this@SyllabusActivity, LessonsActivity::class.java).also {
                    it.putExtra("MODEL",model)
                })
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