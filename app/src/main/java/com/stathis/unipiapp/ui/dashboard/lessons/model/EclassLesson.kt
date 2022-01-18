package com.stathis.unipiapp.ui.dashboard.lessons.model

import com.stathis.unipiapp.models.LocalModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EclassLesson(

    val title : String,
    val code : String,
    val professor : String

) : LocalModel, Parcelable{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is EclassLesson -> title == obj.title && code == obj.code
        else -> false
    }
}