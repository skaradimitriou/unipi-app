package com.stathis.unipiapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stathis.unipiapp.models.LocalModel

class MyBindingAdapters {

    companion object{
        @BindingAdapter("adapter")
        @JvmStatic
        fun setRecyclerViewAdapter(recycler : RecyclerView, adapter: androidx.recyclerview.widget.ListAdapter<LocalModel,RecyclerView.ViewHolder>){
            recycler.adapter = adapter
        }
    }
}