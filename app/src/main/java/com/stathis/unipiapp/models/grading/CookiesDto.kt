package com.stathis.unipiapp.models.grading

import com.stathis.unipiapp.models.LocalModel

data class CookiesDto(
    val token: String
) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is CookiesDto -> token == obj.token
        else -> false
    }
}
