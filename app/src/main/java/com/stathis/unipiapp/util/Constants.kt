package com.stathis.unipiapp.util

import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.stathis.unipiapp.R
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.ui.department.adapter.CarouselAdapter
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


const val CS_UNIPI = "https://www.cs.unipi.gr/"
const val ECLASS_URL = "http://gunet2.cs.unipi.gr"
const val STUDENTS_URL = "https://students.unipi.gr/login.asp?mnuID=student"

@BindingAdapter("loadLocalPhoto")
fun ImageView.loadLocalPhoto(photo: String) {
    try {
        val myImage = this.context.resources.getIdentifier(photo, "drawable", "com.stathis.unipiapp")
        this.setImageResource(myImage)
    } catch (e: Exception) {
        this.setImageResource(R.drawable.ic_unipi_logo_svg)
    }
}

@BindingAdapter("loadProfessorImage")
fun ImageView.loadProfessorImg(model: Professor) {
    val errorImg = when (model.gender) {
        resources.getString(R.string.male) -> R.drawable.male_professor
        resources.getString(R.string.female) -> R.drawable.female_professor
        else -> 0
    }

    model.image?.let {
        Glide.with(this.context).load(model.image).centerCrop()
            .placeholder(errorImg)
            .into(this)
    }
}

@BindingAdapter("setLocalImage")
fun ImageView.setLocalImage(image: Int) {
    try {
        this.setImageResource(image)
    } catch (e: Exception) {
        this.setImageResource(R.drawable.ic_unipi_logo_svg)
    }
}

@BindingAdapter("mandatoryColor")
fun View.setMandatoryColor(mandatory: Boolean) = when (mandatory) {
    true -> this.setBackgroundResource(R.color.unipi_blue)
    false -> this.setBackgroundResource(R.color.unipi_red)
}

@BindingAdapter("setDefaultDate")
fun TextView.setDefaultDate(date: String?) {
    try {
        var formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss")
        date?.let {
            var newDate: Date = formatter.parse(date.take(25))

            formatter = SimpleDateFormat("dd/MM/yyyy")
            this.text = formatter.format(newDate)
        }
    } catch (e: Exception) {
        date?.let { this.text = it.take(16) }
        Timber.d("ERROR CASE => IT")
    }
}

@BindingAdapter("setAuthorEmail")
fun TextView.setAuthorEmail(author: String?) {
    author?.let {
        this.text = String.format(resources.getString(R.string.author), author)
    }
}

@BindingAdapter("setAnnouncementDate")
fun TextView.setAnnouncementDate(date: String?) {
    try {
        var formatter = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss")
        date?.let {
            var newDate: Date = formatter.parse(date.take(25))

            formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
            this.text = formatter.format(newDate)
        }
    } catch (e: Exception) {
        date?.let { this.text = it.take(16) }
        Timber.d("ERROR CASE => IT")
    }
}

@BindingAdapter("setHtmlText")
fun TextView.setText(text: String) {
    this.text = text.toNonHtmlText()
}

@BindingAdapter("setSpanText")
fun TextView.setSpanText(text: String) {
    this.text = text.toNonHtmlText()
}

@BindingAdapter("setScrollableViewPager")
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