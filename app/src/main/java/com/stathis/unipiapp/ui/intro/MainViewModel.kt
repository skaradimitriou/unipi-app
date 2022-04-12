package com.stathis.unipiapp.ui.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.di.student.DaggerStudentApiComponent
import com.stathis.unipiapp.models.Result
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.network.students.StudentsApiClient
import com.stathis.unipiapp.util.UNIPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var apiClient: StudentsApiClient

    val data = MutableLiveData<Result<StudentsResponseDto>>()

    init {
        DaggerStudentApiComponent.create().inject(this)
    }

    fun validateUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            apiClient.postStudentData(username, password, UNIPI, data)
        }
    }
}