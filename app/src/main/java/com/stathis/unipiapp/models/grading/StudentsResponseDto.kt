package com.stathis.unipiapp.models.grading

data class StudentsResponseDto(
    val system: String,
    val cookies: CookiesDto,
    val student: StudentCardDto
)
