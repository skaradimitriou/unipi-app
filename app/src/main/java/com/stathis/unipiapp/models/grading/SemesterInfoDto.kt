package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class SemesterInfoDto(
    val id : Int,
    val passedCourses : Int,
    val gradeAverage : String,
    val ects : String,
    val courses : List<CoursesInfoDto>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is SemesterInfoDto -> id == obj.id && passedCourses == obj.passedCourses && gradeAverage == obj.gradeAverage && courses == obj.courses
        else -> false
    }
}
