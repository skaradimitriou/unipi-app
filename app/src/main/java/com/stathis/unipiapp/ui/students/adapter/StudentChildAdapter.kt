package com.stathis.unipiapp.ui.students.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderServicesItemBinding
import com.stathis.unipiapp.models.LocalModel

class StudentChildAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, StudentChildViewHolder>(MyDiffUtil<LocalModel>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentChildViewHolder {
       val view = HolderServicesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentChildViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: StudentChildViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}