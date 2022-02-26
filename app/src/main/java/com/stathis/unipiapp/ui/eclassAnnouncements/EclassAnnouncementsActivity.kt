package com.stathis.unipiapp.ui.eclassAnnouncements

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.EclassAnnouncementsCallback
import com.stathis.unipiapp.databinding.ActivityEclassAnnouncementsBinding
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement

class EclassAnnouncementsActivity : UnipiActivity<ActivityEclassAnnouncementsBinding>(R.layout.activity_eclass_announcements) {

    private lateinit var viewModel: EclassAnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(EclassAnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        binding.viewModel = viewModel

        val model = intent.getParcelableExtra<EclassLesson>("LESSON")
        model?.let {
            supportActionBar?.title = resources.getString(R.string.lesson_announcements)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.getData(it.code)
        }

        observe()

        binding.swipe2refreshLayout.setOnRefreshListener {
            model?.let { viewModel.getData(it.code) }
        }
    }

    override fun stopOps() = viewModel.release(this)

    private fun observe(){
        viewModel.observe(this, object : EclassAnnouncementsCallback{
            override fun onEclassAnnouncementTap(model: EclassAnnouncement) {
                startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(model.link) })
            }
        })

        viewModel.data.observe(this){
            when(!it.itemList.isNullOrEmpty()){
                true -> binding.swipe2refreshLayout.isRefreshing = false
            }
        }

        viewModel.error.observe(this) {
            when(it){
                true -> Snackbar.make(binding.eclassAnnouncementsParent,getString(R.string.snackbar_error), Snackbar.LENGTH_LONG).show()
                false -> Unit
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }
}