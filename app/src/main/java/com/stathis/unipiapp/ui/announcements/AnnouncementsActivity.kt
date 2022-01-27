package com.stathis.unipiapp.ui.announcements

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.databinding.ActivityAnnouncementsBinding
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.ui.webview.WebviewActivity
import com.stathis.unipiapp.util.BASE_URL

class AnnouncementsActivity : UnipiActivity<ActivityAnnouncementsBinding>(R.layout.activity_announcements) {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        supportActionBar?.title = resources.getString(R.string.announcements)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.viewModel = viewModel

        binding.announcementsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                when(viewModel.adapter.currentList.get(0)){
                    is Announcement -> {
                        when(!recyclerView.canScrollVertically(1)){
                            true ->  viewModel.loadMore()
                            else -> Unit
                        }
                    }
                }
            }
        })

        viewModel.observe(this, object : AnnouncementCallback {
            override fun openAnnouncement(model: Announcement) {
                startActivity(Intent(this@AnnouncementsActivity,WebviewActivity::class.java).also {
                    it.putExtra(resources.getString(R.string.model),model)
                })
            }
        })

        viewModel.error.observe(this, Observer {
            when(it){
                true -> showSnack()
                false -> Unit
            }
        })
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