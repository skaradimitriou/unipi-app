package com.stathis.unipiapp.ui.announcements.adapter

import androidx.databinding.ViewDataBinding
import com.stathis.unipiapp.BR
import com.stathis.unipiapp.abstraction.UnipiViewHolder
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement

class AnnouncementsViewHolder(val binding: ViewDataBinding, val callback : UnipiCallback) : UnipiViewHolder(binding) {

    override fun present(data: LocalModel) {
        when(data){
            is DeptAnnouncement -> {
                binding.setVariable(BR.model,data)
                binding.setVariable(BR.callback,callback)
            }
        }
    }
}
