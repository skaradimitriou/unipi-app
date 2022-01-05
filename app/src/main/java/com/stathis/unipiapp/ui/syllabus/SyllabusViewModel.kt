package com.stathis.unipiapp.ui.syllabus

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
import com.stathis.unipiapp.callbacks.SemesterCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.Semester
import com.stathis.unipiapp.ui.syllabus.adapter.SemesterAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class SyllabusViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    val adapter = SemesterAdapter(this)
    private lateinit var callback: SemesterCallback
    private lateinit var semesterList : MutableList<Semester>
    private val data = MutableLiveData<List<Semester>>()

    init {
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            getLessons()
        }
    }

    private fun getLessons() {
        try {
            val jsonString = app.assets.open("msc_informatics_lessons.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<Semester>>() {}.type
            semesterList = Gson().fromJson(jsonString, listPersonType)
            Log.d(app.getString(R.string.app_name),semesterList.toString())
            data.postValue(semesterList)
        } catch (ioException: IOException) {}
    }

    fun observe(owner: LifecycleOwner, callback : SemesterCallback) {
        this.callback = callback

        data.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when(view.tag){
        is Semester -> callback.onSemesterTap(view.tag as Semester)
        else -> Unit
    }
}