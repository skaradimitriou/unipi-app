package com.stathis.unipiapp.ui.dashboard.main.bottomsheet.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderAllCategoriesItemBinding
import com.stathis.unipiapp.models.LocalModel

class ShortCategoriesAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, ShortCategoriesViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortCategoriesViewHolder {
        val view = HolderAllCategoriesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShortCategoriesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ShortCategoriesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}