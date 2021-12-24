package com.stathis.unipiapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(

    val title : String,
    val code : String,
    val mandatory : Boolean,
    val hours : Int,
    val ects : Int,
    val professor: List<Professor>

) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj) {
        is Lesson -> title == obj.title
        else -> false
    }
}
