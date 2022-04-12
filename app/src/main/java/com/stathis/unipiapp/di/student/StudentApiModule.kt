package com.stathis.unipiapp.di.student

import com.stathis.unipiapp.network.students.StudentsApiClient
import com.stathis.unipiapp.network.students.StudentsEndpoints
import com.stathis.unipiapp.util.STUDENTS_API_BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class StudentApiModule {

    @Provides
    fun provideApi() : StudentsEndpoints {
        val logger = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor(logger).build()

        return Retrofit.Builder()
            .baseUrl(STUDENTS_API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentsEndpoints::class.java)
    }

    @Singleton
    @Provides
    fun provideService() : StudentsApiClient {
        return StudentsApiClient()
    }
}