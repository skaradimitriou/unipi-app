package com.stathis.unipiapp.ui.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderServicesItemBinding
import com.stathis.unipiapp.models.LocalModel

class ServicesAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel,ServicesViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        val view = HolderServicesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ServicesViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ServicesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}