package com.stathis.unipiapp.ui.dashboard.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderHomeItemBinding
import com.stathis.unipiapp.models.LocalModel

class MainScreenAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, MainScreenViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        val view = HolderHomeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainScreenViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}