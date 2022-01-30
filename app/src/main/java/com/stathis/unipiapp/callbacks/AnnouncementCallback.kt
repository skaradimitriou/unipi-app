package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement

interface AnnouncementCallback {
    fun openAnnouncement(model : DeptAnnouncement)
}