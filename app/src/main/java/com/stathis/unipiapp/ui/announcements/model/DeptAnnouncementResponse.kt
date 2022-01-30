package com.stathis.unipiapp.ui.announcements.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class DeptAnnouncementResponse @JvmOverloads constructor(

    @field: Element(name = "channel")
    var channel: DeptChannel? = null
)