package com.stathis.unipiapp.ui.dashboard.announcements

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.databinding.FragmentAnnouncementsBinding
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.util.BASE_URL


class AnnouncementsFragment : UnipiFragment<FragmentAnnouncementsBinding>(R.layout.fragment_announcements) {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        /*
         * FIXME: Add in app webview to open urls
         *        Implement pagination to get more announcements from page on user scroll
         */


        activity?.title = resources.getString(R.string.menu_announcements)

        binding.announcementsRecycler.adapter = viewModel.adapter

        viewModel.observe(this, object : AnnouncementCallback{
            override fun openAnnouncement(model: Announcement) {
                startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(BASE_URL+model.url) })
            }
        })
    }

    override fun stopOps() = viewModel.release(this)
}