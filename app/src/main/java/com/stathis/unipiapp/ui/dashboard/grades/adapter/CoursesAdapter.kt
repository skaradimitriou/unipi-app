package com.stathis.unipiapp.ui.dashboard.grades.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderCoursesItemBinding
import com.stathis.unipiapp.models.LocalModel

class CoursesAdapter(val callback: UnipiCallback) : ListAdapter<LocalModel, CoursesViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val view = HolderCoursesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}