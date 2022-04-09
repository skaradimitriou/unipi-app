package com.stathis.unipiapp.ui.dashboard.grades.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.databinding.HolderGradeParentItemBinding
import com.stathis.unipiapp.models.LocalModel

class GradesAdapter() : ListAdapter<LocalModel, GradesViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradesViewHolder {
        val view = HolderGradeParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GradesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}