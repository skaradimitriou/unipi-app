package com.stathis.unipiapp.ui.students.model

import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.models.UnipiService

data class StudentResponse(

    val carouselItems : List<CarouselItem>,
    val services : List<UnipiService>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentResponse -> obj.carouselItems == carouselItems && obj.services == services
        else -> false
    }
}
