package com.stathis.unipiapp.ui.department.model

import com.stathis.unipiapp.models.LocalModel

data class HorizontalDepartmentItem(val title : String, val list : List<LocalModel>) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is HorizontalDepartmentItem -> title == obj.title && list == obj.list
        else -> false
    }
}