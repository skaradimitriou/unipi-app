package com.stathis.unipiapp.ui.department.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderEmptyLayoutBinding
import com.stathis.unipiapp.databinding.HolderHomeItemBinding
import com.stathis.unipiapp.databinding.ProgrammesItemRowBinding
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.department.model.Programme

class DepartmentChildAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, DepartmentChildViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentChildViewHolder {
        val view = when (viewType) {
            R.layout.programmes_item_row -> ProgrammesItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
           else -> HolderEmptyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return DepartmentChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DepartmentChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is Programme -> R.layout.programmes_item_row
        else -> R.layout.holder_empty_layout
    }
}