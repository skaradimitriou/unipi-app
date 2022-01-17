package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.ui.dashboard.lessons.model.EclassLesson

interface EclassLessonCallback {
    fun onLessonTap(model : EclassLesson)
}