package com.stathis.unipiapp.ui.dashboard.professors.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.LocalModel

class ProfessorsViewHolder(val binding : ViewDataBinding, val callback : UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            // handle professor model
            // add
        }
    }
}