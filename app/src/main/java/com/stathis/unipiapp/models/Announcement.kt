package com.stathis.unipiapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Announcement(val title : String, val date : String, val url : String) : LocalModel, Parcelable {
    override fun equalsContent(obj: LocalModel): Boolean = when(obj){
        is Announcement -> title == obj.title && date == obj.date && url == obj.url
        else -> false
    }
}