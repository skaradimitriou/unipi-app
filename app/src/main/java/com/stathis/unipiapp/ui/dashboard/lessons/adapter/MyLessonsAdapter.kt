package com.stathis.unipiapp.ui.dashboard.lessons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderEclassLessonItemBinding
import com.stathis.unipiapp.models.LocalModel

class MyLessonsAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, MyLessonsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLessonsViewHolder {
        val view = HolderEclassLessonItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyLessonsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: MyLessonsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}