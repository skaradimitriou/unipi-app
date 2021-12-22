package com.stathis.unipiapp.models

data class Professor(val name : String) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Professor -> name == obj.name
        else -> false
    }
}
