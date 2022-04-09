package com.stathis.unipiapp.models.grading

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CookiesDto(
    val token: String? = ""
) : Parcelable,LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CookiesDto -> token == obj.token
        else -> false
    }
}
