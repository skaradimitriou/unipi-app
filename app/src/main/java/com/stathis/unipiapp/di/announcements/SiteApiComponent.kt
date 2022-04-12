package com.stathis.unipiapp.di.announcements

import com.stathis.unipiapp.network.site.SiteApiClient
import com.stathis.unipiapp.ui.announcements.AnnouncementsViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SiteApiModule::class])
interface SiteApiComponent {
    fun inject (api : SiteApiClient)
    fun inject (viewModel : AnnouncementsViewModel)
}