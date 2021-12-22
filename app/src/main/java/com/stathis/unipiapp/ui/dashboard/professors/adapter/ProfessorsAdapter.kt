package com.stathis.unipiapp.ui.dashboard.professors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderProfessorItemBinding
import com.stathis.unipiapp.models.LocalModel

class ProfessorsAdapter(val callback : UnipiCallback) : ListAdapter<LocalModel, ProfessorsViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessorsViewHolder {
        val view = HolderProfessorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProfessorsViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ProfessorsViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}