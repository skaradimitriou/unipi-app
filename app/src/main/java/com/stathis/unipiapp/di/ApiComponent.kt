package com.stathis.unipiapp.di

import com.stathis.unipiapp.network.api.ApiClient
import com.stathis.unipiapp.ui.eclassAnnouncements.EclassAnnouncementsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject (api : ApiClient)
    fun inject (api : EclassAnnouncementsViewModel)
}