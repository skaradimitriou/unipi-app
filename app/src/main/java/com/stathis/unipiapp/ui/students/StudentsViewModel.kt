package com.stathis.unipiapp.ui.students

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.StudentsCallback
import com.stathis.unipiapp.callbacks.UnipiCallback

class StudentsViewModel(val app : Application) : UnipiViewModel(app),UnipiCallback {

    private  lateinit var callback : StudentsCallback
    //val adapter = StudentsAdapter(this)

    //FIXME: Implement Students Screen with 3 carousel items horizontal & student services vertical

    fun observe(owner: LifecycleOwner,callback: StudentsCallback){
        this.callback = callback

    }

    fun release(owner: LifecycleOwner){
        //data.removeObservers(owner)
    }

    override fun onItemTap(view: View) {
        //
    }
}