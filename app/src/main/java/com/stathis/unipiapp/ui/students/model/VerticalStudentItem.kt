package com.stathis.unipiapp.ui.students.model

import com.stathis.unipiapp.models.LocalModel

data class VerticalStudentItem(val title : String, val list : List<LocalModel>) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is VerticalStudentItem -> title == obj.title && list == obj.list
        else -> false
    }
}