package com.stathis.unipiapp.models

data class Lesson(

    val title : String

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj) {
        is Lesson -> title == obj.title
        else -> false
    }
}
