package com.stathis.unipiapp.ui.professors.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.models.Professor

class ProfessorsViewHolder(val binding : ViewDataBinding, val callback : UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
           is Professor ->{
               binding.setVariable(BR.model,data)
               binding.setVariable(BR.callback,callback)
           }
        }
    }
}