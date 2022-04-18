package com.stathis.unipiapp.ui.eclassAnnouncements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderAnnouncementShimmerItemBinding
import com.stathis.unipiapp.databinding.HolderEclassAnnouncementItemBinding
import com.stathis.unipiapp.databinding.HolderEmptyLayoutBinding
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement

class EclassAnnouncementsAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, EclassAnnouncementsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): EclassAnnouncementsViewHolder {
        val view = when(viewType){
            R.layout.holder_eclass_announcement_item -> HolderEclassAnnouncementItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_announcement_shimmer_item -> HolderAnnouncementShimmerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return EclassAnnouncementsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: EclassAnnouncementsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is EclassAnnouncement -> R.layout.holder_eclass_announcement_item
        is ShimmerModel -> R.layout.holder_announcement_shimmer_item
        else -> R.layout.holder_empty_layout
    }
}