package com.stathis.unipiapp.ui.announcements.model

import android.os.Parcelable
import com.stathis.unipiapp.models.LocalModel
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
@Parcelize
data class DeptAnnouncement (

    @field: Element(name = "title")
    var title: String = "",

    @field: Element(name = "description", required = false)
    var description: String = "",

    @field: Element(name = "link")
    var link: String = "",

    @field: Element(name = "pubDate")
    var pubDate: String = ""

) : Parcelable,LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is DeptAnnouncement -> title == obj.title
        else -> false
    }
}