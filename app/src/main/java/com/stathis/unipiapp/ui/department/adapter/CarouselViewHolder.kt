package com.stathis.unipiapp.ui.department.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.LocalModel

class CarouselViewHolder(val binding: ViewDataBinding, val callback: UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when (data) {
            is CarouselItem -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}