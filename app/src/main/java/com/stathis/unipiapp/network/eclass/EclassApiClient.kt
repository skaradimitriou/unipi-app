package com.stathis.unipiapp.network.eclass

import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.di.eclass.DaggerEclassApiComponent
import com.stathis.unipiapp.ui.eclassAnnouncements.model.Channel
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncementResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class EclassApiClient {

    @Inject
    lateinit var api : EclassEndpoints

    init {
        DaggerEclassApiComponent.create().inject(this)
    }

    fun getLessonsAnnouncements(code : String, data: MutableLiveData<Channel>, error: MutableLiveData<Boolean>) {
        api.getLessonAnnouncements(code).enqueue(object : Callback<EclassAnnouncementResponse> {
            override fun onResponse(call: Call<EclassAnnouncementResponse>, response: Response<EclassAnnouncementResponse>) {
                Timber.d(response.body().toString())
                error.postValue(false)

                response.body()?.let {
                    data.postValue(it.channel)
                }
            }

            override fun onFailure(call: Call<EclassAnnouncementResponse>, t: Throwable) {
                Timber.d(t.message.toString())
                error.postValue(true)
            }
        })
    }
}