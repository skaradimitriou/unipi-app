package com.stathis.unipiapp.models

data class Professor(

    val fullName : String,
    val title : String,
    val office : String,
    val telephone : String,
    val email : String,
    val image : String

    ) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Professor -> fullName == obj.fullName
        else -> false
    }
}
