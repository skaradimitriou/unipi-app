package com.stathis.unipiapp.ui.announcements

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.di.announcements.DaggerSiteApiComponent
import com.stathis.unipiapp.models.EmptyItem
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.network.site.SiteApiClient
import com.stathis.unipiapp.ui.announcements.adapter.AnnouncementAdapter
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement
import com.stathis.unipiapp.ui.announcements.model.DeptChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnnouncementsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var api: SiteApiClient

    val adapter = AnnouncementAdapter(this)
    val data = MutableLiveData<DeptChannel>()
    val error = MutableLiveData<Boolean>()
    private lateinit var callback: AnnouncementCallback

    init {
        DaggerSiteApiComponent.create().inject(this)

        getData()
    }

    private fun startShimmer() {
        adapter.submitList(
            listOf(
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel()
            )
        )
    }

    fun stopShimmer() {
        adapter.submitList(listOf(EmptyItem(), EmptyItem()))
    }

    fun getData() {
        startShimmer()

        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                api.getDepartmentAnnouncements(data, error)
            }.onFailure {
                error.postValue(true)
            }
        }
    }

    fun observe(owner: LifecycleOwner, callback: AnnouncementCallback) {
        this.callback = callback

        data.observe(owner, Observer {
            it?.let { adapter.submitList(it.itemList) }
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is DeptAnnouncement -> callback.openAnnouncement(view.tag as DeptAnnouncement)
        else -> Unit
    }
}