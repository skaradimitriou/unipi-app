package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.ContactItem

interface ContactCallback {
    fun onItemTap(model : ContactItem)
}