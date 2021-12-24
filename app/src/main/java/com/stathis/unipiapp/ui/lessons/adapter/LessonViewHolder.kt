package com.stathis.unipiapp.ui.lessons.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.models.Lesson
import com.stathis.unipiapp.models.LocalModel

class LessonViewHolder(val binding : ViewDataBinding) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is Lesson -> binding.setVariable(BR.model, data)
        }
    }
}