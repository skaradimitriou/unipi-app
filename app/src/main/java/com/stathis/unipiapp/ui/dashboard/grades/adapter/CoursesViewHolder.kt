package com.stathis.unipiapp.ui.dashboard.grades.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.models.grading.CoursesInfoDto

class CoursesViewHolder(val binding: ViewDataBinding) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is CoursesInfoDto -> binding.setVariable(BR.model, data)
        }
    }
}