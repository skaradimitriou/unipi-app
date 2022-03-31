package com.stathis.unipiapp.network.site

import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.di.announcements.DaggerSiteApiComponent
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncementResponse
import com.stathis.unipiapp.ui.announcements.model.DeptChannel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class SiteApiClient {

    @Inject
    lateinit var api : SiteEndpoints

    init {
        DaggerSiteApiComponent.create().inject(this)
    }

    fun getDepartmentAnnouncements(data: MutableLiveData<List<DeptAnnouncement>?>, error: MutableLiveData<Boolean>) {
        api.getDeptAnnouncements("com_k2","itemlist","category","category","16","673","el","feed").enqueue(object : Callback<DeptAnnouncementResponse> {
            override fun onResponse(call: Call<DeptAnnouncementResponse>, response: Response<DeptAnnouncementResponse>) {
                Timber.d(response.body().toString())

                error.postValue(false)

                response.body()?.let {
                    data.postValue(it.channel?.itemList)
                }
            }

            override fun onFailure(call: Call<DeptAnnouncementResponse>, t: Throwable) {
                Timber.d(t.message.toString())
                error.postValue(true)
            }
        })
    }
}