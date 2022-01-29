package com.stathis.unipiapp.ui.dashboard.main

import android.app.Application
import android.view.View
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.MainScreenCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.ShortCategory
import com.stathis.unipiapp.models.UnipiItem
import com.stathis.unipiapp.ui.dashboard.main.adapter.MainScreenAdapter
import kotlinx.coroutines.launch

class MainViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    private lateinit var callback: MainScreenCallback
    val adapter = MainScreenAdapter(this)

    init {
        viewModelScope.launch {
            createList()
        }
    }

    private fun createList() {
        val list = listOf(
            UnipiItem(app.resources.getString(R.string.announcements), R.drawable.ic_announcement),
            UnipiItem(app.resources.getString(R.string.students), R.drawable.ic_students),
            UnipiItem(app.resources.getString(R.string.department), R.drawable.dept_building),
            UnipiItem(app.resources.getString(R.string.professors), R.drawable.ic_professors),
        )
        adapter.submitList(list)
    }

    fun getAllCategories(): List<ShortCategory> {
        return listOf(
            ShortCategory(getString(R.string.announcements)),
            ShortCategory(getString(R.string.department)),
            ShortCategory(getString(R.string.students)),
            ShortCategory(getString(R.string.professors)),
            ShortCategory(getString(R.string.syllabus)),
            ShortCategory(getString(R.string.contact)),
            ShortCategory(getString(R.string.about_app_title))
        )
    }

    fun addListener(callback: MainScreenCallback) {
        this.callback = callback
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is UnipiItem -> callback.onItemTap(view.tag as UnipiItem)
        else -> Unit
    }
}