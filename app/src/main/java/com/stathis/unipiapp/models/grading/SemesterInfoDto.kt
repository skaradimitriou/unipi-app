package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SemesterInfoDto(
    val id: Int,
    val passedCourses: Int,
    val gradeAverage: String,
    val ects: String,
    val courses: List<CoursesInfoDto>
) : Parcelable, LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is SemesterInfoDto -> id == obj.id && passedCourses == obj.passedCourses && gradeAverage == obj.gradeAverage && courses == obj.courses
        else -> false
    }
}
