package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.Semester

interface SemesterCallback {
    fun onSemesterTap(model : Semester)
}