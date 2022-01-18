package com.stathis.unipiapp.ui.eclassAnnouncements

import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityEclassAnnouncementsBinding

class EclassAnnouncementsActivity : UnipiActivity<ActivityEclassAnnouncementsBinding>(R.layout.activity_eclass_announcements) {

    override fun init() {

    }

    override fun startOps() {
        val lessonCode = intent.getStringExtra("LESSON_CODE") ?: ""

        //viewModel.getData(lessonCode)
    }

    override fun stopOps() {

    }
}