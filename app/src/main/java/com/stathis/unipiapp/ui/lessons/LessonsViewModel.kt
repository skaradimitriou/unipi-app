package com.stathis.unipiapp.ui.lessons

import android.app.Application
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.models.InfoModel
import com.stathis.unipiapp.models.Lesson
import com.stathis.unipiapp.models.LocalModel
import com.stathis.unipiapp.ui.lessons.adapter.LessonAdapter

class LessonsViewModel(val app : Application) : UnipiViewModel(app) {

    val adapter = LessonAdapter()

    fun bindData(description : String, lessons : List<Lesson>){
        val list = mutableListOf<LocalModel>()
        list.add(InfoModel(description))
        list.addAll(lessons)
        adapter.submitList(list)
    }
}