package com.stathis.unipiapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Professor(

    val fullName : String,
    val vocative : String,
    val title : String,
    val office : String,
    val telephone : String,
    val email : String,
    val image : String,
    val gender : String

    ) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Professor -> fullName == obj.fullName && title == obj.title && email == obj.email
        else -> false
    }
}
