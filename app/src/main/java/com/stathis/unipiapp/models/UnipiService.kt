package com.stathis.unipiapp.models

data class UnipiService(
    val title : String,
    val imageUrl : String,
    val url : String
) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is UnipiService -> obj.title == title && obj.imageUrl == imageUrl
        else -> false
    }
}
