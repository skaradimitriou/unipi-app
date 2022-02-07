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
import com.stathis.unipiapp.di.eclass.DaggerEclassApiComponent
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.network.eclass.EclassApiClient
import com.stathis.unipiapp.ui.eclassAnnouncements.adapter.EclassAnnouncementsAdapter
import com.stathis.unipiapp.ui.eclassAnnouncements.model.Channel
import com.stathis.unipiapp.ui.eclassAnnouncements.model.EclassAnnouncement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class EclassAnnouncementsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var api : EclassApiClient

    val adapter = EclassAnnouncementsAdapter(this)
    val data = MutableLiveData<Channel>()
    val error = MutableLiveData<Boolean>()
    private lateinit var callback : EclassAnnouncementsCallback

    init {
        startShimmer()

        DaggerEclassApiComponent.create().inject(this)
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
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                api.getLessonsAnnouncements(code,data, error)
            }.onFailure {
                error.postValue(true)
            }
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