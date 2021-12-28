package com.stathis.unipiapp.ui.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderCarouselItemBinding
import com.stathis.unipiapp.databinding.HolderHomeItemBinding
import com.stathis.unipiapp.models.LocalModel

class CarouselAdapter (val callback : UnipiCallback) : ListAdapter<LocalModel, CarouselViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = HolderCarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}