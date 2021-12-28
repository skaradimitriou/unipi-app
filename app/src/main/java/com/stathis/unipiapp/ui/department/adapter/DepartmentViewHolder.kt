package com.stathis.unipiapp.ui.department.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.CarouselParent
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.department.model.HorizontalDepartmentItem
import com.stathis.unipiapp.ui.department.model.VerticalDepartmentItem

class DepartmentViewHolder(val binding : ViewDataBinding, val callback : UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is CarouselParent -> {
                val adapter = CarouselAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.adapter,adapter)
                binding.setVariable(BR.callback,callback)
            }

            is HorizontalDepartmentItem -> {
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.adapter,adapter)
                binding.setVariable(BR.model,data)
            }

            is VerticalDepartmentItem -> {
                val adapter = DepartmentChildAdapter(callback)
                adapter.submitList(data.list)

                binding.setVariable(BR.adapter,adapter)
                binding.setVariable(BR.model,data)
            }
        }
    }
}