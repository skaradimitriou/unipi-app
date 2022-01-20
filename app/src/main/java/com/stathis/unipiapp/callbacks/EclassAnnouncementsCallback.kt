package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement

interface EclassAnnouncementsCallback {
    fun onEclassAnnouncementTap(model : EclassAnnouncement)
}