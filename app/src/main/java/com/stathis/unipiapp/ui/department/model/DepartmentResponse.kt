package com.stathis.unipiapp.ui.department.model

import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.LocalModel

data class DepartmentResponse(

    val carouselItems : List<CarouselItem>,
    val programmes : List<Programme>,
    //val links : List<SocialChannel>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is DepartmentResponse -> carouselItems == obj.carouselItems && programmes == obj.programmes
        else -> false
    }
}