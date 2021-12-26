package com.stathis.unipiapp.ui.students

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityStudentsBinding

class StudentsActivity : UnipiActivity<ActivityStudentsBinding>(R.layout.activity_students) {

    private lateinit var viewModel : StudentsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.students)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun stopOps() {
        //
    }
}