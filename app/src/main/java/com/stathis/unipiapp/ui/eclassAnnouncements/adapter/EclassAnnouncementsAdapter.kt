package com.stathis.unipiapp.ui.eclassAnnouncements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderEclassAnnouncementItemBinding
import com.stathis.unipiapp.models.LocalModel

class EclassAnnouncementsAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, EclassAnnouncementsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): EclassAnnouncementsViewHolder {
        val view = HolderEclassAnnouncementItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EclassAnnouncementsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: EclassAnnouncementsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}