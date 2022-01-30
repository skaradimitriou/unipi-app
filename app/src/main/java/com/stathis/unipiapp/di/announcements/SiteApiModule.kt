package com.stathis.unipiapp.di.announcements

import com.stathis.unipiapp.network.UnsafeOkHttpClient
import com.stathis.unipiapp.network.site.SiteEndpoints
import com.stathis.unipiapp.network.site.SiteApiClient
import com.stathis.unipiapp.util.CS_UNIPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class SiteApiModule {

    @Provides
    fun provideApi() = Retrofit.Builder()
        .baseUrl(CS_UNIPI)
        .client(getOkHttpBuilder().build())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
        .create(SiteEndpoints::class.java)

    @Singleton
    @Provides
    fun provideService() : SiteApiClient {
        return SiteApiClient()
    }

    fun getOkHttpBuilder(): OkHttpClient.Builder = UnsafeOkHttpClient.getClient()
}