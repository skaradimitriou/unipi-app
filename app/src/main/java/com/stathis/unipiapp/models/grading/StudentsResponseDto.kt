package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class StudentsResponseDto(
    val system: String,
    val cookies: CookiesDto,
    val student: StudentCardDto
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentsResponseDto -> system == obj.system && cookies == obj.cookies && student == obj.student
        else -> false
    }
}
