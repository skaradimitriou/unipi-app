package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class StudentCardDto(
    val info: StudentInfoCardDto,
    val grades : StudentGradesCardDto
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentCardDto -> info == obj.info
        else -> false
    }
}
