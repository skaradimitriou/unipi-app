package com.stathis.unipiapp.ui.lessons

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityLessonsBinding
import com.stathis.unipiapp.models.Semester

class LessonsActivity : UnipiActivity<ActivityLessonsBinding>(R.layout.activity_lessons) {

    private lateinit var viewModel : LessonsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
    }

    override fun startOps() {
        val semester = intent.getParcelableExtra<Semester>("MODEL")
        semester?.let { viewModel.bindData(semester.lessons) }

        title = semester?.title

        binding.setVariable(BR.viewModel,viewModel)
    }

    override fun stopOps() {
        //
    }
}