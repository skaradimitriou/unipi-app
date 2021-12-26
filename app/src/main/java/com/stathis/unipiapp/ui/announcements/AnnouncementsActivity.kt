package com.stathis.unipiapp.ui.announcements

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiActivity
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.databinding.ActivityAnnouncementsBinding
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.util.BASE_URL

class AnnouncementsActivity : UnipiActivity<ActivityAnnouncementsBinding>(R.layout.activity_announcements) {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        /*
         * FIXME: Add in app webview to open urls
         *        Implement pagination to get more announcements from page on user scroll
         */

        supportActionBar?.title = resources.getString(R.string.menu_announcements)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.setVariable(BR.viewModel,viewModel)

        viewModel.observe(this, object : AnnouncementCallback {
            override fun openAnnouncement(model: Announcement) {
                startActivity(Intent(Intent.ACTION_VIEW).also { it.data = Uri.parse(BASE_URL +model.url) })
            }
        })
    }

    override fun stopOps() = viewModel.release(this)
}