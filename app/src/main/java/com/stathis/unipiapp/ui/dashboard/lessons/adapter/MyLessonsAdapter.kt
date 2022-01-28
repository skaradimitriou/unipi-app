package com.stathis.unipiapp.ui.dashboard.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.facebook.shimmer.Shimmer
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderEclassLessonItemBinding
import com.stathis.unipiapp.databinding.HolderEclassLessonShimmerItemBinding
import com.stathis.unipiapp.databinding.HolderEmptyLayoutBinding
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson

class MyLessonsAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, MyLessonsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLessonsViewHolder {
        val view = when(viewType){
            R.layout.holder_eclass_lesson_shimmer_item -> HolderEclassLessonShimmerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            R.layout.holder_eclass_lesson_item -> HolderEclassLessonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            else -> HolderEmptyLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        }

        return MyLessonsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: MyLessonsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)){
        is ShimmerModel -> R.layout.holder_eclass_lesson_shimmer_item
        is EclassLesson -> R.layout.holder_eclass_lesson_item
        else -> R.layout.holder_empty_layout
    }
}