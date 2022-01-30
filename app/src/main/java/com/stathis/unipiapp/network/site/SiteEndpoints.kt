package com.stathis.unipiapp.network.site

import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncementResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SiteEndpoints {

    @GET("index.php")
    fun getDeptAnnouncements(
        @Query("option") option : String,
        @Query("view") view : String,
        @Query("layout") layout : String,
        @Query("task") task : String,
        @Query("id") id : String,
        @Query("Itemid") Itemid : String,
        @Query("lang") lang : String,
        @Query("format") format : String,
    ) : Call<DeptAnnouncementResponse>
}