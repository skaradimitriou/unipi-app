package com.stathis.unipiapp.ui.dashboard.syllabus

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.models.Lesson
import com.stathis.unipiapp.ui.dashboard.syllabus.adapter.LessonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyllabusViewModel(val app: Application) : UnipiViewModel(app) {

    val adapter = LessonAdapter()
    private val data = MutableLiveData<List<Lesson>>()

    init {
        getData()
    }

    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            getLessons()
        }
    }

    private fun getLessons() {
        val dummyList = listOf(
            Lesson("Εισαγωγή στην Επιστήμη των Υπολογιστών"),
            Lesson("Αρχές Προγραμματισμού – Γλώσσα C, C++"),
            Lesson("Δομές Δεδομένων"),
            Lesson("Γλώσσες Προγραμματισμού και Μεταγλωττιστές"),
            Lesson("Λειτουργικά Συστήματα")
        )

        data.postValue(dummyList)
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun release(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }
}