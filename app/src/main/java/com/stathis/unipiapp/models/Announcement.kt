package com.stathis.unipiapp.models

data class Announcement(val title : String, val date : String, val url : String) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Announcement -> true
        else -> false
    }
}