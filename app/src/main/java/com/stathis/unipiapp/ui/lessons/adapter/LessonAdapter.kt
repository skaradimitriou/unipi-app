package com.stathis.unipiapp.ui.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.databinding.HolderEmptyLayoutBinding
import com.stathis.unipiapp.databinding.HolderInfoItemBinding
import com.stathis.unipiapp.databinding.HolderLessonItemBinding
import com.stathis.unipiapp.models.InfoModel
import com.stathis.unipiapp.models.Lesson
import com.stathis.unipiapp.models.LocalModel

class LessonAdapter() : ListAdapter<LocalModel, LessonViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_lesson_item -> HolderLessonItemBinding.inflate(inflater, parent, false)
            R.layout.holder_info_item -> HolderInfoItemBinding.inflate(inflater, parent, false)
            else -> HolderEmptyLayoutBinding.inflate(inflater, parent, false)
        }
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Lesson -> R.layout.holder_lesson_item
        is InfoModel -> R.layout.holder_info_item
        else -> R.layout.holder_empty_layout
    }
}