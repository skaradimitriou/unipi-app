package com.stathis.unipiapp.ui.services

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.ServicesCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.network.JsoupModule
import com.stathis.unipiapp.ui.services.adapter.ServicesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServicesViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    val adapter = ServicesAdapter(this)
    val data = MutableLiveData<List<UnipiService>>()
    val error = MutableLiveData<Boolean>()
    private lateinit var callback : ServicesCallback

    init {
        getData()
    }

    fun observe(owner: LifecycleOwner,callback : ServicesCallback) {
        this.callback = callback

        data.observe(owner, Observer {
            val json = Gson().toJson(it)
            Log.d("",json)
            it?.let { adapter.submitList(it) }
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            JsoupModule.getUnipiServices(data, error)
        }
    }

    override fun onItemTap(view: View) = when(view.tag){
        is UnipiService -> callback.onServiceTap(view.tag as UnipiService)
        else -> Unit
    }
}