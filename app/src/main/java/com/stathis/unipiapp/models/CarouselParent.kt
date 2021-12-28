package com.stathis.unipiapp.models

import com.stathis.unipiapp.models.LocalModel

data class CarouselParent(val list : List<CarouselItem>) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CarouselParent -> list == obj.list
        else -> false
    }
}