package com.stathis.unipiapp.models

data class CarouselItem(

    val imageResource: String,
    val url: String,
    val title: String,
    val position: Int

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is CarouselItem -> obj.title == title && obj.imageResource == imageResource && obj.url == url && obj.position == position
        else -> false
    }
}
