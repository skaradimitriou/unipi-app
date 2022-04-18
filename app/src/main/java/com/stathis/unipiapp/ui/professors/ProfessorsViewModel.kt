package com.stathis.unipiapp.ui.professors

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.ProfessorCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.di.gson.DaggerGsonComponent
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.ui.professors.adapter.ProfessorsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


class ProfessorsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var gson: Gson

    val adapter = ProfessorsAdapter(this)
    private lateinit var professorList: MutableList<Professor>
    val data = MutableLiveData<List<Professor>>()
    private lateinit var callback: ProfessorCallback

    init {
        DaggerGsonComponent.create().inject(this)

        getData()
    }

    fun observe(owner: LifecycleOwner, callback: ProfessorCallback) {
        this.callback = callback

        data.observe(owner) {
            adapter.submitList(it)
        }
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            getProfessors()
        }
    }

    fun getProfessors() {
        try {
            val jsonString = app.assets.open("professors.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Professor>>() {}.type
            professorList = gson.fromJson(jsonString, listPersonType)
            data.postValue(professorList)
        } catch (ioException: IOException) {}
    }

    fun filter(text: String) {
        val filteredList = arrayListOf<Professor>()
        for (item in professorList) {
            if (item.fullName.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        data.value = filteredList
    }

    override fun onItemTap(view: View) {
        when (view.tag) {
            is Professor -> callback.onProfessorTap(view.tag as Professor)
        }
    }
}