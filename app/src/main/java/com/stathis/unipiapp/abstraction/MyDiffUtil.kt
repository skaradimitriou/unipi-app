package com.stathis.unipiapp.abstraction

import androidx.recyclerview.widget.DiffUtil
import com.stathis.unipiapp.models.LocalModel

class MyDiffUtil<T : LocalModel> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.equalsContent(newItem)
    }
}