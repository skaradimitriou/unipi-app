package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentCardDto(
    val info: StudentInfoCardDto?,
    val grades: StudentGradesCardDto?
) : Parcelable, LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is StudentCardDto -> info == obj.info
        else -> false
    }
}
