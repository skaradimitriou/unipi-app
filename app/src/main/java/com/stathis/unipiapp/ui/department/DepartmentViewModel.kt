package com.stathis.unipiapp.ui.department

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
import com.stathis.unipiapp.callbacks.DepartmentCallback
import com.stathis.unipiapp.callbacks.UnipiCallback
import com.stathis.unipiapp.di.gson.DaggerGsonComponent
import com.stathis.unipiapp.models.CarouselItem
import com.stathis.unipiapp.models.CarouselParent
import com.stathis.unipiapp.ui.department.adapter.DepartmentAdapter
import com.stathis.unipiapp.ui.department.model.DepartmentResponse
import com.stathis.unipiapp.ui.department.model.HorizontalDepartmentItem
import com.stathis.unipiapp.ui.department.model.Programme
import com.stathis.unipiapp.ui.department.model.VerticalDepartmentItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class DepartmentViewModel(val app : Application) : UnipiViewModel(app), UnipiCallback {

    @Inject
    lateinit var gson : Gson

    private lateinit var model : DepartmentResponse
    val data = MutableLiveData<DepartmentResponse>()
    val error = MutableLiveData<Boolean>()
    val adapter = DepartmentAdapter(this)
    private lateinit var callback : DepartmentCallback

    init {
        DaggerGsonComponent.create().inject(this)

        getData()
    }

    fun getData(){
        viewModelScope.launch(Dispatchers.IO){ getDepartmentData() }
    }

    fun observeData(owner: LifecycleOwner,callback : DepartmentCallback) {
        this.callback = callback

        data.observe(owner) {
            adapter.submitList(listOf(
                CarouselParent(it.carouselItems),
                VerticalDepartmentItem(getString(R.string.dept_studies),it.programmes)
            ))
        }
    }

    fun release(owner: LifecycleOwner){
        data.removeObservers(owner)
    }

    fun getDepartmentData(){
        try {
            val jsonString = app.assets.open("department_data.json").bufferedReader().use { it.readText() }
            val listPersonType = object : TypeToken<DepartmentResponse>() {}.type

            model  = gson.fromJson(jsonString, listPersonType)

            data.postValue(model)
            error.postValue(false)
        } catch (ioException: IOException) { error.postValue(true) }
    }

    override fun onItemTap(view: View) = when(view.tag){
        is CarouselItem -> callback.openCarouselItem(view.tag as CarouselItem)
        is Programme -> callback.openProgramme(view.tag as Programme)
        else -> Unit
    }
}