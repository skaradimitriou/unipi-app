package com.stathis.unipiapp.di.student

import com.stathis.unipiapp.network.students.StudentsApiClient
import com.stathis.unipiapp.ui.intro.MainViewModel
import com.stathis.unipiapp.ui.login.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StudentApiModule::class])
interface StudentApiComponent {
    fun inject(apiClient : StudentsApiClient)
    fun inject(mainViewModel: MainViewModel)
    fun inject(loginViewModel: LoginViewModel)
}