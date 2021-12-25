package com.stathis.unipiapp.ui.dashboard.professors

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.ProfessorCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.network.JsoupModule
import com.stathis.unipiapp.ui.dashboard.professors.adapter.ProfessorsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class ProfessorsViewModel(val app : Application) : UnipiViewModel(app),UnipiCallback {

    val adapter = ProfessorsAdapter(this)
    private lateinit var professorList: MutableList<Professor>
    val data = MutableLiveData<List<Professor>>()
    private lateinit var callback : ProfessorCallback

    init {
        getData()
    }

    fun observe(owner: LifecycleOwner,callback: ProfessorCallback){
        this.callback = callback

        data.observe(owner, Observer{
            adapter.submitList(it)
        })
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            getProfessors()
        }
    }

    fun getProfessors(){
        try {
            val jsonString = app.assets.open("professors.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Professor>>() {}.type
            professorList = Gson().fromJson(jsonString, listPersonType)
            data.postValue(professorList)
        } catch (ioException: IOException) {}
    }

    override fun onItemTap(view: View) {
        when(view.tag){
            is Professor -> callback.onProfessorTap(view.tag as Professor)
        }
    }
}