package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class CoursesInfoDto(
    val id: String,
    val name: String,
    val type: String,
    val grade: String,
    val examPeriod: String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CoursesInfoDto -> id == obj.id && name == obj.name && type == obj.type && grade == obj.grade && examPeriod == obj.examPeriod
        else -> false
    }
}
