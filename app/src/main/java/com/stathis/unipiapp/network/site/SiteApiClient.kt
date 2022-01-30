package com.stathis.unipiapp.network.site

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.di.announcements.DaggerSiteApiComponent
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncementResponse
import com.stathis.unipiapp.ui.announcements.model.DeptChannel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SiteApiClient {

    @Inject
    lateinit var api : SiteEndpoints

    init {
        DaggerSiteApiComponent.create().inject(this)
    }

    fun getDepartmentAnnouncements(data: MutableLiveData<DeptChannel>, error: MutableLiveData<Boolean>) {
        api.getDeptAnnouncements("com_k2","itemlist","category","category","16","673","el","feed").enqueue(object : Callback<DeptAnnouncementResponse> {
            override fun onResponse(call: Call<DeptAnnouncementResponse>, response: Response<DeptAnnouncementResponse>) {
                Log.d("Request body", response.body().toString())

                error.postValue(false)

                response.body()?.let {
                    data.postValue(it.channel)
                }
            }

            override fun onFailure(call: Call<DeptAnnouncementResponse>, t: Throwable) {
                Log.d("Request body", t.message.toString())
                error.postValue(true)
            }
        })
    }
}