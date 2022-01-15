package com.stathis.unipiapp.network.api

import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("/manuals/manual.php")
    fun getLessons() : Call<String>
}