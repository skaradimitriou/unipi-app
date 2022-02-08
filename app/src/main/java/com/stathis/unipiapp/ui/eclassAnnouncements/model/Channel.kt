package com.stathis.unipiapp.ui.eclassAnnouncements.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(

    @field: Element(name = "title")
    var title: String = "",

    @field: ElementList(inline = true)
    var itemList: List<EclassAnnouncement>? = null
)