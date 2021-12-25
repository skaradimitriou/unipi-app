package com.stathis.unipiapp.ui.dashboard.main

import android.app.Application
import android.view.View
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.MainScreenCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.UnipiItem
import com.stathis.unipiapp.ui.dashboard.main.adapter.MainScreenAdapter

class MainViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    private lateinit var callback: MainScreenCallback
    val adapter = MainScreenAdapter(this)

    init {
        createList()
    }

    private fun createList() {
        val list = listOf(
            UnipiItem("Item 1"),
            UnipiItem("Item 2"),
            UnipiItem("Item 3"),
            UnipiItem("Item 4"),
        )
        adapter.submitList(list)
    }

    fun addListener(callback: MainScreenCallback) {
        this.callback = callback
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is UnipiItem -> callback.onItemTap(view.tag as UnipiItem)
        else -> Unit
    }
}