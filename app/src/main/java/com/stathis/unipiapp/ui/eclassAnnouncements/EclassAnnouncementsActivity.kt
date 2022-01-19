package com.stathis.unipiapp.ui.eclassAnnouncements

import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.databinding.ActivityEclassAnnouncementsBinding
import com.stathis.unipiapp.models.Semester
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement

class EclassAnnouncementsActivity : UnipiActivity<ActivityEclassAnnouncementsBinding>(R.layout.activity_eclass_announcements) {

    private lateinit var viewModel: EclassAnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(EclassAnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        val model = intent.getParcelableExtra<EclassLesson>("LESSON")
        model?.let {
            supportActionBar?.title = resources.getString(R.string.lesson_announcements)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.getData(it.code)
        }

        binding.viewModel = viewModel
        


        viewModel.observe(this)
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