package com.stathis.unipiapp.ui.dashboard.announcements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderAnnouncementItemBinding
import com.stathis.unipiapp.models.LocalModel

class AnnouncementAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, AnnouncementsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val view = HolderAnnouncementItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AnnouncementsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}