package com.stathis.unipiapp.ui.announcements

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.network.JsoupModule
import com.stathis.unipiapp.ui.announcements.adapter.AnnouncementAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnnouncementsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    val adapter = AnnouncementAdapter(this)
    private val data = MutableLiveData<List<Announcement>>()
    private val error = MutableLiveData<Boolean>()
    private var tempList = mutableListOf<Announcement>()
    private lateinit var callback: AnnouncementCallback
    private var counter = 0

    init {
        startShimmer()
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

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            JsoupModule.getAnnouncements(tempList,data,error,counter)
        }
    }

    fun observe(owner: LifecycleOwner, callback: AnnouncementCallback) {
        this.callback = callback

        data.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is Announcement -> callback.openAnnouncement(view.tag as Announcement)
        else -> Unit
    }

    fun loadMore() {
        counter += 14
        getData()
    }
}