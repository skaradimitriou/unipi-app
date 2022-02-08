package com.stathis.unipiapp.ui.eclassAnnouncements.model

import com.stathis.unipiapp.models.LocalModel
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class EclassAnnouncement (

    @field: Element(name = "title")
    var title: String = "",

    @field: Element(name = "description", required = false)
    var description: String = "",

    @field: Element(name = "link")
    var link: String = "",

    @field: Element(name = "pubDate")
    var pubDate: String = ""

) : LocalModel {
    override fun equalsContent(obj: LocalModel): Boolean = when (obj) {
        is EclassAnnouncement -> title == obj.title
        else -> false
    }
}