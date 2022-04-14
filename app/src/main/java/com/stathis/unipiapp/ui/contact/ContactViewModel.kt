package com.stathis.unipiapp.ui.contact

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.callbacks.ContactCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.di.gson.DaggerGsonComponent
import com.stathis.unipiapp.models.ContactItem
import com.stathis.unipiapp.ui.contact.adapter.ContactAdapter
import java.io.IOException
import javax.inject.Inject

class ContactViewModel(val app : Application) : AndroidViewModel(app), UnipiCallback {

    @Inject
    lateinit var gson: Gson

    val adapter = ContactAdapter(this)
    private lateinit var list : MutableList<ContactItem>
    val data = MutableLiveData<List<ContactItem>>()
    private lateinit var callback : ContactCallback

    init {
        DaggerGsonComponent.create().inject(this)

        getData()
    }

    fun observe(owner : LifecycleOwner, callback : ContactCallback){
        this.callback = callback

        data.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    fun getData(){
        try {
            val jsonString = app.assets.open("contact_data.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<ContactItem>>() {}.type
            list = gson.fromJson(jsonString, listPersonType)
            data.postValue(list)
        } catch (ioException: IOException) {}
    }

    override fun onItemTap(view: View) = when(view.tag){
        is ContactItem -> callback.onItemTap(view.tag as ContactItem)
        else -> Unit
    }
}
