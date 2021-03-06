package com.stathis.unipiapp.ui.announcements

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.database.UnipiDatabase
import com.stathis.unipiapp.di.announcements.DaggerSiteApiComponent
import com.stathis.unipiapp.models.EmptyItem
import com.stathis.unipiapp.network.site.SiteApiClient
import com.stathis.unipiapp.ui.announcements.adapter.AnnouncementAdapter
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement
import com.stathis.unipiapp.util.SharedPrefsHelper
import com.stathis.unipiapp.util.ShimmerHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnnouncementsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var api: SiteApiClient

    val adapter = AnnouncementAdapter(this)
    val data = MutableLiveData<List<DeptAnnouncement>?>()
    val error = MutableLiveData<Boolean>()

    val database = UnipiDatabase.getDatabase(app).announcementDao()
    private val prefHelper = SharedPrefsHelper.setHelper(app)
    private val refreshTime = 5 * 60 * 1000 * 1000 * 1000L
    private lateinit var callback: AnnouncementCallback

    init {
        DaggerSiteApiComponent.create().inject(this)

        val updateTime = SharedPrefsHelper.getUpdateTime()
        val currentTime = System.nanoTime()

        when(updateTime > 0 && currentTime - updateTime < refreshTime) {
            true -> {
                try {
                    //Get Data From Database
                    getAnnouncementsFromDb()
                } catch (e: Exception) {
                    //Get Data from Web
                    getData()
                }
            }
            false -> getData()
        }
    }

    private fun startShimmer() {
        adapter.submitList(
            ShimmerHelper.getShimmerItems()
        )
    }

    fun stopShimmer() {
        adapter.submitList(listOf(EmptyItem(), EmptyItem()))
    }

    private fun getAnnouncementsFromDb() {
        startShimmer()

        viewModelScope.launch(Dispatchers.IO){
            val announcements = database.getAll()
            if(announcements.isNullOrEmpty()){
                getData()
            } else {
                data.postValue(announcements)
            }
        }
    }

    fun getData() {
        startShimmer()

        viewModelScope.launch(Dispatchers.IO) {
            api.getDepartmentAnnouncements(data, error)
            SharedPrefsHelper.saveUpdateTime(System.nanoTime())
        }
    }

    fun observe(owner: LifecycleOwner, callback: AnnouncementCallback) {
        this.callback = callback

        data.observe(owner) {
            it?.let {
                deleteAllFromDb()
                insertAllToDb(it)

                adapter.submitList(it)
            }
        }
    }

    private fun deleteAllFromDb(){
       viewModelScope.launch(Dispatchers.IO) {
           database.deleteAll()
       }
    }

    private fun insertAllToDb(announcements: List<DeptAnnouncement>) {
        viewModelScope.launch(Dispatchers.IO) {
            database.insertAll(announcements)
        }
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is DeptAnnouncement -> callback.openAnnouncement(view.tag as DeptAnnouncement)
        else -> Unit
    }
}