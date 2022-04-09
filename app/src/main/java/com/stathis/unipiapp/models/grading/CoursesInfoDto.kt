package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoursesInfoDto(
    val id: String,
    val name: String,
    val type: String,
    val grade: String,
    val examPeriod: String
) : Parcelable, LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is CoursesInfoDto -> id == obj.id && name == obj.name && type == obj.type && grade == obj.grade && examPeriod == obj.examPeriod
        else -> false
    }
}
