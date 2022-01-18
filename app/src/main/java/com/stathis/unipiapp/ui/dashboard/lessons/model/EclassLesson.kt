package com.stathis.unipiapp.ui.dashboard.lessons.model

import com.stathis.unipiapp.models.LocalModel

class EclassLesson(
    val title : String,
    val code : String
) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is EclassLesson -> title == obj.title && code == obj.code
        else -> false
    }
}