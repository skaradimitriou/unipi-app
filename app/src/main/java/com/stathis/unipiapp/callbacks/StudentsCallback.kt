package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.UnipiService

interface StudentsCallback {
    fun openCarouselItem(model : CarouselItem)
    fun openServices(model : UnipiService)
}