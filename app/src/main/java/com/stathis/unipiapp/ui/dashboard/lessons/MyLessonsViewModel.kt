package com.stathis.unipiapp.ui.dashboard.lessons

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.EclassLessonCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.ui.dashboard.lessons.adapter.MyLessonsAdapter
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class MyLessonsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    val adapter = MyLessonsAdapter(this)
    val data = MutableLiveData<List<EclassLesson>>()
    private lateinit var eclassLessons: List<EclassLesson>
    private lateinit var callback: EclassLessonCallback

    init {
        viewModelScope.launch { getData() }
    }

    private fun getData() {
        try {
            val jsonString =
                app.assets.open("eclass_lessons_mppl.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<EclassLesson>>() {}.type
            eclassLessons = Gson().fromJson(jsonString, listPersonType)

            data.postValue(eclassLessons)
        } catch (ioException: IOException) {
        }
    }

    fun filter(text: String) {
        val filteredList = arrayListOf<EclassLesson>()
        for (item in eclassLessons) {
            if (item.title.lowercase().contains(text.lowercase())) {
                filteredList.add(item)
            }
        }
        data.value = filteredList
    }

    fun observe(owner: LifecycleOwner, callback: EclassLessonCallback) {
        this.callback = callback

        data.observe(owner, Observer {
            it?.let { adapter.submitList(it) }
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is EclassLesson -> callback.onLessonTap(view.tag as EclassLesson)
        else -> Unit
    }
}