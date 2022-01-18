package com.stathis.unipiapp.ui.eclassAnnouncements.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class EclassAnnouncement @JvmOverloads constructor(

    @field: Element(name = "title")
    var title: String = "",

    @field: Element(name = "description", required = false)
    var description: String = "",

    @field: Element(name = "link")
    var link: String = ""
)