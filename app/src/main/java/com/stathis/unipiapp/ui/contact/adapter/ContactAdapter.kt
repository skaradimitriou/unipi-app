package com.stathis.unipiapp.ui.contact.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.stathis.unipiapp.abstraction.MyDiffUtil
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.databinding.HolderContactItemBinding
import com.stathis.unipiapp.models.LocalModel

class ContactAdapter(val callback: UnipiCallback) : ListAdapter<LocalModel, ContactViewHolder>(MyDiffUtil<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = HolderContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(view,callback)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}