package com.stathis.unipiapp.network.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.di.DaggerApiComponent
import com.stathis.unipiapp.ui.eclassAnnouncements.model.Channel
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncementResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ApiClient {

    @Inject
    lateinit var api : Endpoints

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getLessonsAnnouncements(code : String, data: MutableLiveData<Channel>, error: MutableLiveData<Boolean>) {
        api.getLessonAnnouncements(code).enqueue(object : Callback<EclassAnnouncementResponse> {
            override fun onResponse(call: Call<EclassAnnouncementResponse>, response: Response<EclassAnnouncementResponse>) {
                Log.d("Request body", response.body().toString())

                error.postValue(false)

                response.body()?.let {
                    data.postValue(it.channel)
                }
            }

            override fun onFailure(call: Call<EclassAnnouncementResponse>, t: Throwable) {
                Log.d("Request body", t.message.toString())
                error.postValue(true)
            }
        })
    }
}