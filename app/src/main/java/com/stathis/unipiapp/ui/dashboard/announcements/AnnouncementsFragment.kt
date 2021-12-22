package com.stathis.unipiapp.ui.dashboard.announcements

import androidx.lifecycle.ViewModelProvider
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiFragment
import com.stathis.unipiapp.databinding.FragmentAnnouncementsBinding


class AnnouncementsFragment : UnipiFragment<FragmentAnnouncementsBinding>(R.layout.fragment_announcements) {

    private lateinit var viewModel : AnnouncementsViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(AnnouncementsViewModel::class.java)
    }

    override fun startOps() {
        binding.name = resources.getString(R.string.menu_announcements)
    }

    override fun stopOps() {
        //
    }
}