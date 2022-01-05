package com.stathis.unipiapp.network.api

import com.stathis.unipiapp.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Endpoints::class.java)

    fun getLessons() {
        api.getLessons()
    }
}