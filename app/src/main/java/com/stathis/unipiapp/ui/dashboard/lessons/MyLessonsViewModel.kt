package com.stathis.unipiapp.ui.dashboard.lessons

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.EclassLessonCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.di.gson.DaggerGsonComponent
import com.stathis.unipiapp.ui.dashboard.lessons.adapter.MyLessonsAdapter
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson
import com.stathis.unipiapp.util.ShimmerHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

class MyLessonsViewModel(val app: Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var gson : Gson

    val adapter = MyLessonsAdapter(this)
    val data = MutableLiveData<List<EclassLesson>>()
    val error = MutableLiveData<Boolean>()
    private lateinit var eclassLessons: List<EclassLesson>
    private lateinit var callback: EclassLessonCallback

    init {
        DaggerGsonComponent.create().inject(this)

        getData()
    }

    private fun startShimmer() {
        adapter.submitList(
            ShimmerHelper.getShimmerItems()
        )
    }

    private fun getData() {
        startShimmer()

        viewModelScope.launch(Dispatchers.IO) {
            runBlocking {
                getEclassLessons()
            }
        }
    }

    private fun getEclassLessons(){
        try {
            val jsonString = app.assets.open("eclass_lessons_mppl.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<List<EclassLesson>>() {}.type
            eclassLessons = gson.fromJson(jsonString, listPersonType)

            data.postValue(eclassLessons)
            error.postValue(false)
        } catch (ioException: IOException) {
            error.postValue(true)
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

        data.observe(owner) {
            it?.let { adapter.submitList(it) }
        }
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    override fun onItemTap(view: View) = when (view.tag) {
        is EclassLesson -> callback.onLessonTap(view.tag as EclassLesson)
        else -> Unit
    }
}