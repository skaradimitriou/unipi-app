package com.stathis.unipiapp.util

import androidx.databinding.BindingAdapter
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.squareup.picasso.Picasso
import com.stathis.unipiapp.R
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.ui.department.adapter.CarouselAdapter
import org.jsoup.Jsoup


class MyBindingAdapters {

    companion object {
        @BindingAdapter("loadLocalPhoto")
        @JvmStatic
        fun ImageView.loadLocalPhoto(photo: String) {
            try {
                val myImage =
                    this.context.resources.getIdentifier(photo, "drawable", "com.stathis.unipiapp")
                this.setImageResource(myImage)
            } catch (e: Exception) {
                this.setImageResource(R.drawable.ic_unipi_logo_svg)
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun ImageView.loadImg(url: String?) {
            url?.let {
                Picasso.get().load(url).error(R.drawable.ic_unipi_logo_svg).into(this)
            }
        }

        @BindingAdapter("loadProfessorImage")
        @JvmStatic
        fun ImageView.loadProfessorImg(model: Professor) {
            val errorImg = when (model.gender) {
                resources.getString(R.string.male) -> R.drawable.male_professor
                resources.getString(R.string.female) -> R.drawable.female_professor
                else -> 0
            }

            model.image?.let {
                Picasso.get().load(model.image).error(errorImg).into(this)
            }
        }

        @BindingAdapter("setLocalImage")
        @JvmStatic
        fun ImageView.setLocalImage(image: Int) {
            try {
                this.setImageResource(image)
            } catch (e: Exception) {
                this.setImageResource(R.drawable.ic_unipi_logo_svg)
            }
        }


        @BindingAdapter("setDate")
        @JvmStatic
        fun TextView.setDate(date: String) {
            this.text = date.take(16)
        }

        @BindingAdapter("setHtmlText")
        @JvmStatic
        fun TextView.setText(text: String) {
            this.text = Jsoup.parse(text).text()
        }

        @BindingAdapter("setScrollableViewPager")
        @JvmStatic
        fun setScrollableViewPager(viewPager: ViewPager2, adapter: CarouselAdapter) {
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