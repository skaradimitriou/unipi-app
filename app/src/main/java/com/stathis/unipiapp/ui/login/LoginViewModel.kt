package com.stathis.unipiapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.network.students.StudentsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val isLoggedIn = MutableLiveData<Boolean>()

    fun validateUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            StudentsApi.postStudentData(username, password, "GUEST")
            isLoggedIn.postValue(true)
        }
    }
}