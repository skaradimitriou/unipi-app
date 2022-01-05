package com.stathis.unipiapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stathis.unipiapp.models.LocalModel
import android.R
import android.os.Handler
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import com.stathis.unipiapp.ui.department.adapter.CarouselAdapter


class MyBindingAdapters {

    companion object{
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun ImageView.loadImg(url: String?) {
            url?.let {
                Picasso.get().load(url).error(R.drawable.stat_notify_error).into(this)
            }
        }

        @BindingAdapter("setLocalImage")
        @JvmStatic
        fun ImageView.setLocalImage(image: Int) {
            this.setImageResource(image)
        }

        @BindingAdapter("adapter")
        @JvmStatic
        fun setRecyclerViewAdapter(recycler : RecyclerView, adapter: androidx.recyclerview.widget.ListAdapter<LocalModel,RecyclerView.ViewHolder>){
            recycler.adapter = adapter
        }
        @BindingAdapter("setScrollableViewPager")
        @JvmStatic
        fun setScrollableViewPager(viewPager : ViewPager2, adapter : CarouselAdapter){
            val sliderHandler = Handler()

            val sliderRunnable = Runnable {
                when (viewPager.currentItem == adapter.itemCount - 1) {
                    true -> viewPager.currentItem = 0
                    else -> viewPager.currentItem = viewPager.currentItem + 1
                }
            }

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, 2500)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    when (state == ViewPager2.SCROLL_STATE_IDLE) {
                        true -> sliderHandler.postDelayed(sliderRunnable, 2500)
                    }
                }
            })
        }
    }
}