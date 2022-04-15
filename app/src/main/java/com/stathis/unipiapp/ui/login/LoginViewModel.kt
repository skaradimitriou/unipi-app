package com.stathis.unipiapp.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stathis.unipiapp.abstraction.UnipiViewModel
import com.stathis.unipiapp.di.student.DaggerStudentApiComponent
import com.stathis.unipiapp.models.Result
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.network.students.StudentsApiClient
import com.stathis.unipiapp.util.UNIPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel(val app: Application) : UnipiViewModel(app) {

    @Inject
    lateinit var apiClient: StudentsApiClient

    @Inject
    lateinit var gson: Gson

    val data = MutableLiveData<Result<StudentsResponseDto>>()

    init {
        DaggerStudentApiComponent.create().inject(this)
    }

    fun validateUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiClient.postStudentData(username, password, UNIPI, data)
        }
    }

    fun loginGuestUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val json = app.assets.open("guest_user.json").bufferedReader().use { it.readText() }
                val type = object : TypeToken<StudentsResponseDto>() {}.type
                val model: StudentsResponseDto = gson.fromJson(json, type)
                data.postValue(Result.Success(model))
            } catch (e: Exception) {
                Timber.d("$e")
                data.postValue(Result.Error("Could not login as a guest"))
            }
        }
    }
}