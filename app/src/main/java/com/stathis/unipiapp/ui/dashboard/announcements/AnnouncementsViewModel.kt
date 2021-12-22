package com.stathis.unipiapp.ui.dashboard.announcements

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.Announcement
import com.stathis.unipiapp.ui.dashboard.announcements.adapter.AnnouncementAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class AnnouncementsViewModel(val app : Application) : UnipiViewModel(app), UnipiCallback {

    val adapter = AnnouncementAdapter(this)
    private val data = MutableLiveData<List<Announcement>>()
    private lateinit var callback : AnnouncementCallback

    init {
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            getAnnouncements()
        }
    }

    fun observe(owner: LifecycleOwner, callback: AnnouncementCallback){
        this.callback = callback

        data.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun release(owner : LifecycleOwner){
        data.removeObservers(owner)
    }

    fun getAnnouncements() {
        val announcements = arrayListOf<Announcement>()

        try {
            val url = "https://www.cs.unipi.gr/index.php?option=com_k2&view=itemlist&layout=category&task=category&id=16&Itemid=673&lang=el"
            val doc = Jsoup.connect(url).timeout(60000).validateTLSCertificates(false).get()
            for (i in 0..9) {
                val title = doc.select(".catItemView").select(".catItemHeader").select("h3").select("a").eq(i).text()
                val date = doc.select(".catItemView").select(".blog-item-meta").select(".catItemDateCreated").eq(i).text()
                val url = doc.select(".catItemView").select(".catItemHeader").select("h3").select("a").attr("href")
                announcements.add(Announcement(title,date,url))
            }

            data.postValue(announcements)
        } catch (e: Exception) {
            Log.d(app.resources.getString(R.string.app_name), e.toString())
        }
    }

    override fun onItemTap(view: View) = when(view.tag){
        is Announcement -> callback.openAnnouncement(view.tag as Announcement)
        else -> Unit
    }
}