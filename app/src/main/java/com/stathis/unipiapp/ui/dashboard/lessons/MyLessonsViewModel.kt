package com.stathis.unipiapp.ui.dashboard.lessons

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.EclassLessonCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.ui.dashboard.lessons.adapter.MyLessonsAdapter
import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson

class MyLessonsViewModel(app: Application) : UnipiViewModel(app), UnipiCallback {

    val adapter = MyLessonsAdapter(this)
    val user = MutableLiveData<String>()
    val data = MutableLiveData<List<EclassLesson>>()
    private lateinit var callback: EclassLessonCallback

    init {
        doStuff()
        getDummyData()
    }

    private fun doStuff() {
        user.value = "myUsername"
    }

    private fun getDummyData() {
        val list = listOf(
            EclassLesson(getString(R.string.eclass_lesson_title)),
            EclassLesson(getString(R.string.eclass_lesson_title)),
            EclassLesson(getString(R.string.eclass_lesson_title)),
            EclassLesson(getString(R.string.eclass_lesson_title)),
            EclassLesson(getString(R.string.eclass_lesson_title)),
            EclassLesson(getString(R.string.eclass_lesson_title))
        )

        data.value = list
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