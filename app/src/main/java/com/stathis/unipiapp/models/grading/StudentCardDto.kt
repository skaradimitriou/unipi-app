package com.stathis.unipiapp.models.grading

data class StudentCardDto(
    val info: StudentInfoCardDto,
    val grades : StudentGradesCardDto
)
