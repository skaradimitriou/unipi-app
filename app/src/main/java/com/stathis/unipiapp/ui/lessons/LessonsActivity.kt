package com.stathis.unipiapp.ui.lessons

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityLessonsBinding

class LessonsActivity : UnipiActivity<ActivityLessonsBinding>(R.layout.activity_lessons) {

    private lateinit var viewModel : LessonsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(LessonsViewModel::class.java)
    }

    override fun startOps() {
        //
    }

    override fun stopOps() {
        //
    }
}