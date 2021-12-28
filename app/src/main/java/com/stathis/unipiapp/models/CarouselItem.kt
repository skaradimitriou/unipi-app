package com.stathis.unipiapp.models

import com.stathis.unipiapp.models.LocalModel

data class CarouselItem(
    val title : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CarouselItem -> obj.title == title
        else -> false
    }
}
