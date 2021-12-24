package com.stathis.unipiapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Semester (

    val title : String,
    val lessons : List<Lesson>

) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Semester -> obj.title == title && obj.lessons == lessons
        else -> false
    }
}
