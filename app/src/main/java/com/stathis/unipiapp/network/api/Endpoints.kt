package com.stathis.unipiapp.network.api

import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncementResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    @GET("/manuals/manual.php")
    fun getLessons(): Call<String>

    @GET("modules/announcements/rss.php")
    fun getLessonAnnouncements(
        @Query("c") code: String
    ): Call<EclassAnnouncementResponse>
}