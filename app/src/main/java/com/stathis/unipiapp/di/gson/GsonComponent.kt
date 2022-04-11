package com.stathis.unipiapp.di.gson

import com.stathis.unipiapp.ui.intro.MainActivity
import com.stathis.unipiapp.ui.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [GsonModule::class])
interface GsonComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
}