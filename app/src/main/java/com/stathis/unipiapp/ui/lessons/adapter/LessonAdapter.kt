package com.stathis.unipiapp.ui.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.databinding.HolderLessonItemBinding
import com.stathis.unipiapp.models.LocalModel

class LessonAdapter() : ListAdapter<LocalModel, LessonViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = HolderLessonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}