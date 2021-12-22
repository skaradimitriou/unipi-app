package com.stathis.unipiapp.abstraction

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class UnipiViewModel(app : Application) : AndroidViewModel(app) {

    fun getString(id : Int) = getApplication<Application>().resources.getString(id)
}