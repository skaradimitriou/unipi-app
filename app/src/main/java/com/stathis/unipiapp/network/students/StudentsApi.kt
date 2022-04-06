package com.stathis.unipiapp.network.students

import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object StudentsApi {

    val BASE_URL = "https://unistudents-prod-1.herokuapp.com/api/"

    private val api  = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StudentsEndpoints::class.java)

    fun postStudentData(university : String, loginForm: LoginForm) {
        return api.postStudentData(university,loginForm).enqueue(object : Callback<StudentsResponseDto> {
            override fun onResponse(call: Call<StudentsResponseDto>, response: Response<StudentsResponseDto>) {
                Timber.d("DATA => ${response.body()}")
            }

            override fun onFailure(call: Call<StudentsResponseDto>, t: Throwable) {
                Timber.d("${t.localizedMessage}")
            }
        })
    }
}