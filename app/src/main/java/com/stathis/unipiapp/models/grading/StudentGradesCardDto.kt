package com.stathis.unipiapp.models.grading

data class StudentGradesCardDto(
    val totalPassedCourses: String,
    val totalAverageGrade: String,
    val totalEcts: String,
    val semesters: List<SemesterInfoDto>
)
