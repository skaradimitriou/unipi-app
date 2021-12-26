package com.stathis.unipiapp.ui.announcements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderAnnouncementItemBinding
import com.stathis.unipiapp.databinding.HolderAnnouncementShimmerItemBinding
import com.stathis.unipiapp.databinding.HolderEmptyLayoutBinding
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.models.ShimmerModel

class AnnouncementAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, AnnouncementsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when(viewType){
            R.layout.holder_announcement_item -> HolderAnnouncementItemBinding.inflate(inflater,parent,false)
            R.layout.holder_announcement_shimmer_item -> HolderAnnouncementShimmerItemBinding.inflate(inflater,parent,false)
            else -> HolderEmptyLayoutBinding.inflate(inflater,parent,false)
        }
        return AnnouncementsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: AnnouncementsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is ShimmerModel -> R.layout.holder_announcement_shimmer_item
        is Announcement -> R.layout.holder_announcement_item
        else -> R.layout.holder_empty_layout
    }
}