package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class StudentInfoCardDto(
    val aem : String,
    val firstName : String,
    val lastName : String,
    val department : String,
    val semester : String,
    val registrationYear : String
) : Parcelable,LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is StudentInfoCardDto -> aem == obj.aem && firstName == obj.firstName && lastName == obj.lastName && semester == obj.semester
        else -> false
    }
}
