package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class StudentGradesCardDto(
    val totalPassedCourses: String,
    val totalAverageGrade: String,
    val totalEcts: String,
    val semesters: List<SemesterInfoDto>
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is StudentGradesCardDto -> totalPassedCourses == obj.totalPassedCourses && totalAverageGrade == obj.totalAverageGrade && semesters == obj.semesters
        else -> false
    }
}
