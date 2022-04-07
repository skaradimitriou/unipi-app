package com.stathis.unipiapp.ui.dashboard.grades.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.models.grading.SemesterInfoDto
import com.stathis.unipiapp.models.grading.StudentGradesCardDto

class GradesViewHolder(val binding: ViewDataBinding, val callback: UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is SemesterInfoDto -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)

                val adapter = CoursesAdapter(callback)
                binding.setVariable(BR.adapter,adapter)
                adapter.submitList(data.courses)
            }
        }
    }
}