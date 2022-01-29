package com.stathis.unipiapp.ui.dashboard.syllabus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderSemesterItemBinding
import com.stathis.unipiapp.models.LocalModel

class SemesterAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, SemesterViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemesterViewHolder {
        val view = HolderSemesterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SemesterViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SemesterViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}