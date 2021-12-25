package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.UnipiItem

interface MainScreenCallback {
    fun onItemTap(model : UnipiItem)
}