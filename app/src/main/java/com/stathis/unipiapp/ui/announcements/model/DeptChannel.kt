package com.stathis.unipiapp.ui.announcements.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class DeptChannel @JvmOverloads constructor(

    @field: Element(name = "title")
    var title: String = "",

    @field: ElementList(inline = true)
    var itemList: List<DeptAnnouncement>? = null
)