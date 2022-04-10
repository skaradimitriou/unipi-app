package com.stathis.unipiapp.ui.students

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.R
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.callbacks.StudentsCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.CarouselParent
import com.stathis.unipiapp.models.UnipiService
import com.stathis.unipiapp.ui.department.model.DepartmentResponse
import com.stathis.unipiapp.ui.department.model.VerticalDepartmentItem
import com.stathis.unipiapp.ui.students.adapter.StudentsAdapter
import com.stathis.unipiapp.ui.students.model.StudentResponse
import com.stathis.unipiapp.ui.students.model.VerticalStudentItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class StudentsViewModel(val app : Application) : UnipiViewModel(app),UnipiCallback {

    private  lateinit var callback : StudentsCallback
    val adapter = StudentsAdapter(this)
    private lateinit var model : StudentResponse
    val data = MutableLiveData<StudentResponse>()
    val error = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO){
            getStudentData()
        }
    }

    fun observe(owner: LifecycleOwner,callback: StudentsCallback){
        this.callback = callback

        data.observe(owner) {
            adapter.submitList(listOf(
                CarouselParent(it.carouselItems),
                VerticalStudentItem(getString(R.string.student_services),it.services)
            ))
        }
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    fun getStudentData(){
        try {
            val jsonString = app.assets.open("students_screen_data.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<StudentResponse>() {}.type

            model  = Gson().fromJson(jsonString, listPersonType)

            data.postValue(model)
            error.postValue(false)
        } catch (ioException: IOException) { error.postValue(true) }
    }

    override fun onItemTap(view: View) = when(view.tag){
        is CarouselItem -> callback.openCarouselItem(view.tag as CarouselItem)
        is UnipiService -> callback.openServices(view.tag as UnipiService)
        else -> Unit
    }
}