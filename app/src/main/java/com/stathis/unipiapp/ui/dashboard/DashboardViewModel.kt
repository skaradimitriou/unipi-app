package com.stathis.unipiapp.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    val activeUser = MutableLiveData<StudentsResponseDto>()

    fun setActiveUser(user: StudentsResponseDto) {
        viewModelScope.launch(Dispatchers.IO){
            activeUser.postValue(user)
        }
    }
}