package com.stathis.unipiapp.abstraction

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.stathis.unipiapp.models.LocalModel

abstract class UnipiViewHolder(itemView : ViewDataBinding) : RecyclerView.ViewHolder(itemView.root) {

    fun bindData(data: LocalModel) {
        itemView.tag = data
        present(data)
    }

    abstract fun present(data: LocalModel)
}