package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentGradesCardDto(
    val totalPassedCourses: String,
    val totalAverageGrade: String,
    val totalEcts: String,
    val semesters: List<SemesterInfoDto>
) : Parcelable, LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is StudentGradesCardDto -> totalPassedCourses == obj.totalPassedCourses && totalAverageGrade == obj.totalAverageGrade && semesters == obj.semesters
        else -> false
    }
}
