package com.stathis.unipiapp.ui.contact.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.models.ContactItem
import com.stathis.unipiapp.models.LocalModel

class ContactViewHolder(val binding : ViewDataBinding, val callback : UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is ContactItem -> {
                binding.setVariable(BR.model,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}