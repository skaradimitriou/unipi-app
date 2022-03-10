package com.stathis.unipiapp.ui.announcements

import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.AnnouncementCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.database.UnipiDatabase
import com.stathis.unipiapp.di.announcements.DaggerSiteApiComponent
import com.stathis.unipiapp.models.EmptyItem
import com.stathis.unipiapp.models.ShimmerModel
import com.stathis.unipiapp.network.site.SiteApiClient
import com.stathis.unipiapp.ui.announcements.adapter.AnnouncementAdapter
import com.stathis.unipiapp.ui.announcements.model.DeptAnnouncement
import com.stathis.unipiapp.ui.announcements.model.DeptChannel
import com.stathis.unipiapp.util.ShimmerHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AnnouncementsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var api: SiteApiClient

    val adapter = AnnouncementAdapter(this)
    val data = MutableLiveData<DeptChannel>()
    val error = MutableLiveData<Boolean>()

    val database = UnipiDatabase.getDatabase(app).announcementDao()
    private lateinit var callback: AnnouncementCallback

    init {
        DaggerSiteApiComponent.create().inject(this)

        getData()
    }

    private fun startShimmer() {
        adapter.submitList(
            ShimmerHelper.getShimmerItems()
        )
    }

    fun stopShimmer() {
        adapter.submitList(listOf(EmptyItem(), EmptyItem()))
    }

    fun getData() {
        startShimmer()

        getAnnouncementsFromDb()

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
            it?.let {
                insertAllToDb(it.itemList!!)
                adapter.submitList(it.itemList)
            }
        })
    }

    private fun getAnnouncementsFromDb(){
        viewModelScope.launch(Dispatchers.IO){
            val data = database.getAll()
            Timber.d("DATA => $data")
        }
    }

    private fun deleteAllFromDb(){
       viewModelScope.launch(Dispatchers.IO) {
           database.deleteAll()
       }
    }

    private fun insertAllToDb(announcements: List<DeptAnnouncement>){
        viewModelScope.launch(Dispatchers.IO){
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