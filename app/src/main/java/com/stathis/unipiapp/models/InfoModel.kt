package com.stathis.unipiapp.models

data class InfoModel(
    val description : String
) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is InfoModel -> description == obj.description
        else -> false
    }
}
