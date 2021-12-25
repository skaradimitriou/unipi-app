package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.UnipiService

interface ServicesCallback {
    fun onServiceTap(model : UnipiService)
}