package com.stathis.unipiapp.network.eclass

import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncementResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EclassEndpoints {

    @GET("modules/announcements/rss.php")
    fun getLessonAnnouncements(
        @Query("c") code: String
    ): Call<EclassAnnouncementResponse>
}