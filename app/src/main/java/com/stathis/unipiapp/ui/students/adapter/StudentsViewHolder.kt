package com.stathis.unipiapp.ui.students.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.models.CarouselParent
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.department.adapter.CarouselAdapter
import com.stathis.unipiapp.ui.students.model.VerticalStudentItem

class StudentsViewHolder(val binding : ViewDataBinding, val callback : UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
           is CarouselParent -> {
               val adapter = CarouselAdapter(callback)
               adapter.submitList(data.list)

               binding.setVariable(BR.adapter,adapter)
               binding.setVariable(BR.model,data)
           }
            is VerticalStudentItem -> {
                val adapter = StudentChildAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.adapter,adapter)
                binding.setVariable(BR.model,data)
            }
        }
    }
}