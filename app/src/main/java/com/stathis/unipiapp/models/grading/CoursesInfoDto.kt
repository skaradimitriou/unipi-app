package com.stathis.unipiapp.models.grading

data class CoursesInfoDto(
    val id: String,
    val name: String,
    val type: String,
    val grade: String,
    val examPeriod: String
)
