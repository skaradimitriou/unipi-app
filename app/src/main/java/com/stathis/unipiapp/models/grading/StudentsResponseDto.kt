package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentsResponseDto(
    val system: String?,
    val cookies: CookiesDto?,
    val student: StudentCardDto?
) : Parcelable, LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentsResponseDto -> system == obj.system && cookies == obj.cookies && student == obj.student
        else -> false
    }
}
