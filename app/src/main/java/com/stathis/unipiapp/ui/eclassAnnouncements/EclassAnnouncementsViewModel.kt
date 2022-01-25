package com.stathis.unipiapp.ui.eclassAnnouncements

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.EclassAnnouncementsCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.di.DaggerApiComponent
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.network.api.ApiClient
import com.stathis.unipiapp.network.api.Endpoints
import com.stathis.unipiapp.ui.eclassAnnouncements.adapter.EclassAnnouncementsAdapter
import com.stathis.unipiapp.ui.eclassAnnouncements.model.Channel
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement
import kotlinx.coroutines.launch
import javax.inject.Inject

class EclassAnnouncementsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var api : ApiClient

    val adapter = EclassAnnouncementsAdapter(this)
    val data = MutableLiveData<Channel>()
    val error = MutableLiveData<Boolean>()
    private lateinit var callback : EclassAnnouncementsCallback

    init {
        startShimmer()

        DaggerApiComponent.create().inject(this)
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

    fun getData(code: String) {
        viewModelScope.launch {
            api.getLessonsAnnouncements(code,data, error)
        }
    }

    fun observe(owner: LifecycleOwner, callback : EclassAnnouncementsCallback) {
        this.callback = callback

        data.observe(owner, Observer {
            it?.let { adapter.submitList(it.itemList) }
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is EclassAnnouncement -> callback.onEclassAnnouncementTap(view.tag as EclassAnnouncement)
        else -> Unit
    }
}