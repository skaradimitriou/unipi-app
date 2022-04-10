package com.stathis.unipiapp.ui.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.network.students.StudentsApi
import com.stathis.unipiapp.util.UNIPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val data = MutableLiveData<StudentsResponseDto>()
    val error = MutableLiveData<Boolean>()

    fun validateUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            StudentsApi.postStudentData(username, password, UNIPI, data, error)
        }
    }
}