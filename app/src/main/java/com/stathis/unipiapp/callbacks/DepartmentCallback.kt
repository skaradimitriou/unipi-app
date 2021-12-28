package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.ui.department.model.Programme

interface DepartmentCallback {
    fun openCarouselItem(model : CarouselItem)
    fun openProgramme(model : Programme)
}