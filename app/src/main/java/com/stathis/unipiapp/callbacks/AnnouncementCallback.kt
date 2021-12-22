package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.Announcement

interface AnnouncementCallback {
    fun openAnnouncement(model : Announcement)
}