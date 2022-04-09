package com.stathis.unipiapp.ui.dashboard.grades.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.models.grading.SemesterInfoDto

class GradesViewHolder(val binding: ViewDataBinding) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is SemesterInfoDto -> {
                binding.setVariable(BR.model, data)

                val adapter = CoursesAdapter()
                binding.setVariable(BR.adapter,adapter)
                adapter.submitList(data.courses)
            }
        }
    }
}