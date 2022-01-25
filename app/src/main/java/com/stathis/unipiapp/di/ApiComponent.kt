package com.stathis.unipiapp.di

import com.stathis.unipiapp.network.api.ApiClient
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject (api : ApiClient)
}