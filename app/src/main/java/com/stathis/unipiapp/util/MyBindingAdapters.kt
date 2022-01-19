package com.stathis.unipiapp.util

import androidx.databinding.BindingAdapter
import android.R
import android.os.Handler
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import com.stathis.unipiapp.ui.department.adapter.CarouselAdapter
import org.jsoup.Jsoup


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

        @BindingAdapter("setDate")
        @JvmStatic
        fun TextView.setDate(date: String) {
            this.text = date.take(16)
        }

        @BindingAdapter("setLessonText")
        @JvmStatic
        fun TextView.setLessonText(text: String) {
            this.text = text.toLowerCase().capitalizeWords()
        }

        @BindingAdapter("setHtmlText")
        @JvmStatic
        fun TextView.setText(text: String) {
            this.text = Jsoup.parse(text).text()
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