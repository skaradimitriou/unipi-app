package com.stathis.unipiapp.models.grading

data class SemesterInfoDto(
    val id : Int,
    val passedCourses : Int,
    val gradeAverage : String,
    val ects : String,
    val courses : List<CoursesInfoDto>
)
