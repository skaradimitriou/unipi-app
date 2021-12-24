package com.stathis.unipiapp.models

data class Lesson(

    val title : String,
    val code : String,
    val mandatory : Boolean,
    val hours : Int,
    val ects : Int,
    val professor: List<Professor>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj) {
        is Lesson -> title == obj.title
        else -> false
    }
}
