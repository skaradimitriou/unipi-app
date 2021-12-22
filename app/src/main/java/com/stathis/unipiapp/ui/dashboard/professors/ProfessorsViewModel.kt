package com.stathis.unipiapp.ui.dashboard.professors

import android.app.Application
import android.view.View
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.ui.dashboard.professors.adapter.ProfessorsAdapter

class ProfessorsViewModel(val app : Application) : UnipiViewModel(app),UnipiCallback {

    val adapter = ProfessorsAdapter(this)

    init {
        createDummyList()
    }

    private fun createDummyList() {
        //
    }

    override fun onItemTap(view: View) {
        when(view.tag){
            // handle callbacks
        }
    }
}