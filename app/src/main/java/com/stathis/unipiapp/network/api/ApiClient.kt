package com.stathis.unipiapp.network.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.util.ECLASS_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    /*
    FIXME: Get data from Unipi's eclass.

           - Authenticate User in Eclass platform & then:
           - Get Lessons that I am registered to
           - Get my latest announcements
     */

    private val api = Retrofit.Builder().baseUrl(ECLASS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Endpoints::class.java)

    fun getLessons(data: MutableLiveData<String>, error: MutableLiveData<Boolean>) {
        api.getLessons().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("Request body", response.body().toString())

                error.value = false
                data.value = response.body().toString()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("Request body", t.message.toString())
                error.value = true
            }
        })
    }
}