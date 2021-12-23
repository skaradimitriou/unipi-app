package com.stathis.unipiapp.callbacks

import com.stathis.unipiapp.models.Professor

interface ProfessorCallback {
    fun onProfessorTap(model : Professor)
}