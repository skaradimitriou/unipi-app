package com.stathis.unipiapp.di.eclass

import com.stathis.unipiapp.network.eclass.EclassApiClient
import com.stathis.unipiapp.ui.eclassAnnouncements.EclassAnnouncementsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [EclassApiModule::class])
interface EclassApiComponent {
    fun inject (api : EclassApiClient)
    fun inject (viewModel : EclassAnnouncementsViewModel)
}