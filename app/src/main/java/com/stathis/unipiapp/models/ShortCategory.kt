package com.stathis.unipiapp.models

data class ShortCategory(
    val title : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is ShortCategory -> obj.title == title
        else -> false
    }
}
