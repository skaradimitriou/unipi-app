package com.stathis.unipiapp.ui.lessons

import android.app.Application
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.models.Lesson
import com.stathis.unipiapp.ui.lessons.adapter.LessonAdapter

class LessonsViewModel(val app : Application) : UnipiViewModel(app) {

    val adapter = LessonAdapter()

    fun bindData(list : List<Lesson>){
        adapter.submitList(list)
    }
}