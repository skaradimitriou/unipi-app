package com.stathis.unipiapp.ui.eclassAnnouncements.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "rss", strict = false)
data class EclassAnnouncementResponse (

    @field: Element(name = "channel")
    var channel: Channel? = null
)
