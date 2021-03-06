package com.stathis.unipiapp.models

data class UnipiItem(

    val title : String,
    val img : Int

) : LocalModel{
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is UnipiItem -> obj.title == title
        else -> false
    }
}
