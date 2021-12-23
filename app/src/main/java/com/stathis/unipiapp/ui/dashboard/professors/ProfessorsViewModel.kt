package com.stathis.unipiapp.ui.dashboard.professors

import android.app.Application
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.ProfessorCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.Professor
import com.stathis.unipiapp.ui.dashboard.professors.adapter.ProfessorsAdapter

class ProfessorsViewModel(val app : Application) : UnipiViewModel(app),UnipiCallback {

    val adapter = ProfessorsAdapter(this)
    val data = MutableLiveData<List<Professor>>()
    private lateinit var callback : ProfessorCallback

    init {
        createDummyList()
    }

    fun observe(owner: LifecycleOwner,callback: ProfessorCallback){
        this.callback = callback

        data.observe(owner, Observer{
            adapter.submitList(it)
        })
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    private fun createDummyList() {
        val list = listOf(
            Professor("Πατσάκης Κωνσταντίνος","Επίκουρος Καθηγητής","540/ΚΕΚΤ","+30 210 4142261","kpatsak@unipi.gr"),
            Professor("Πατσάκης Κωνσταντίνος","Επίκουρος Καθηγητής","540/ΚΕΚΤ","+30 210 4142261","kpatsak@unipi.gr"),
            Professor("Πατσάκης Κωνσταντίνος","Επίκουρος Καθηγητής","540/ΚΕΚΤ","+30 210 4142261","kpatsak@unipi.gr"),
            Professor("Πατσάκης Κωνσταντίνος","Επίκουρος Καθηγητής","540/ΚΕΚΤ","+30 210 4142261","kpatsak@unipi.gr"),
            Professor("Πατσάκης Κωνσταντίνος","Επίκουρος Καθηγητής","540/ΚΕΚΤ","+30 210 4142261","kpatsak@unipi.gr"),
            Professor("Πατσάκης Κωνσταντίνος","Επίκουρος Καθηγητής","540/ΚΕΚΤ","+30 210 4142261","kpatsak@unipi.gr"),
        )

        data.value = list
    }

    override fun onItemTap(view: View) {
        when(view.tag){
            is Professor -> callback.onProfessorTap(view.tag as Professor)
        }
    }
}