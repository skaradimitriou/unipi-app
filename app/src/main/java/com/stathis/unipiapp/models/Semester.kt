package com.stathis.unipiapp.models

data class Semester (

    val title : String,
    val lessons : List<Lesson>

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Semester -> obj.title == title && obj.lessons == lessons
        else -> false
    }
}
