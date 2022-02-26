package com.stathis.unipiapp.ui.announcements

import android.content.Intent
import android.net.Uri
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.databinding.ActivityAnnouncementsBinding
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement

class AnnouncementsActivity : UnipiActivity<ActivityAnnouncementsBinding>(R.layout.activity_announcements) {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.announcements)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel

        observe()

        binding.swipe2refreshLayout.setOnRefreshListener {
            viewModel.getData()
        }
    }

    private fun observe() {
        viewModel.observe(this, object : AnnouncementCallback {
            override fun openAnnouncement(model: DeptAnnouncement) {
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
                true -> showSnack()
                false -> Unit
            }
        }
    }

    override fun stopOps() = viewModel.release(this)

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> false
    }

    fun showSnack(){
        viewModel.stopShimmer()

        Snackbar.make(binding.announcementsParent,getString(R.string.snackbar_error), Snackbar.LENGTH_LONG).also {
            it.setAction(resources.getString(R.string.retry)) {
                viewModel.getData()
            }
        }.show()
    }
}