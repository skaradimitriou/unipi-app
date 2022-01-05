package com.stathis.unipiapp.network.api

import retrofit2.http.GET

interface Endpoints {

    @GET("")
    fun getLessons()
}