package com.stathis.unipiapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stathis.unipiapp.models.LocalModel
import android.R
import android.widget.ImageView
import com.squareup.picasso.Picasso


class MyBindingAdapters {

    companion object{
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun ImageView.loadImg(url: String) {
            Picasso.get().load(url).error(R.drawable.stat_notify_error).into(this)
        }

        @BindingAdapter("adapter")
        @JvmStatic
        fun setRecyclerViewAdapter(recycler : RecyclerView, adapter: androidx.recyclerview.widget.ListAdapter<LocalModel,RecyclerView.ViewHolder>){
            recycler.adapter = adapter
        }
    }
}