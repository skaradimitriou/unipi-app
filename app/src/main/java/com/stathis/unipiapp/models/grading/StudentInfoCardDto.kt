package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class StudentInfoCardDto(
    val aem : String,
    val firstName : String,
    val lastName : String,
    val department : String,
    val semester : String,
    val registrationYear : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentInfoCardDto -> aem == obj.aem && firstName == obj.firstName && lastName == obj.lastName && semester == obj.semester
        else -> false
    }
}
