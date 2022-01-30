package com.stathis.unipiapp.di.eclass

import com.stathis.unipiapp.network.UnsafeOkHttpClient
import com.stathis.unipiapp.network.eclass.EclassApiClient
import com.stathis.unipiapp.network.eclass.EclassEndpoints
import com.stathis.unipiapp.util.ECLASS_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class EclassApiModule {

    @Provides
    fun provideApi() : EclassEndpoints {
        return Retrofit.Builder()
            .baseUrl(ECLASS_URL)
            .client(getOkHttpBuilder().build())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(EclassEndpoints::class.java)
    }

    @Singleton
    @Provides
    fun provideService() : EclassApiClient {
        return EclassApiClient()
    }

    fun getOkHttpBuilder(): OkHttpClient.Builder = UnsafeOkHttpClient.getClient()
}