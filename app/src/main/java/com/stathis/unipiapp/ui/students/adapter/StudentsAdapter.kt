package com.stathis.unipiapp.ui.students.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderEmptyLayoutBinding
import com.stathis.unipiapp.databinding.HolderStudHorizontalNestedItemBinding
import com.stathis.unipiapp.databinding.HolderViewpagerCarouselItemBinding
import com.stathis.unipiapp.models.CarouselParent
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.students.model.VerticalStudentItem

class StudentsAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel,StudentsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val view = when(viewType){
            R.layout.holder_viewpager_carousel_item -> HolderViewpagerCarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            R.layout.holder_stud_horizontal_nested_item -> HolderStudHorizontalNestedItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }
        return StudentsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is CarouselParent -> R.layout.holder_viewpager_carousel_item
        is VerticalStudentItem -> R.layout.holder_stud_horizontal_nested_item
        else -> R.layout.holder_empty_view
    }
}