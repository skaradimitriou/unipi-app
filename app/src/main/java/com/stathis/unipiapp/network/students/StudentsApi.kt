package com.stathis.unipiapp.network.students

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.util.STUDENTS_URL
import com.stathis.unipiapp.util.UserAgentGenerator
import org.jsoup.Connection
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object StudentsApi {

    val BASE_URL = "https://unistudents-prod-1.herokuapp.com/api/"

    private val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StudentsEndpoints::class.java)

    fun loginGuestUser(
        data: MutableLiveData<StudentsResponseDto>,
        error: MutableLiveData<Boolean>
    ) {

        val loginForm = LoginForm(
            username = "guest",
            password = "guest",
            cookies = null
        )

        return api.postStudentData("GUEST", loginForm)
            .enqueue(object : Callback<StudentsResponseDto> {
                override fun onResponse(
                    call: Call<StudentsResponseDto>,
                    response: Response<StudentsResponseDto>
                ) {
                    response.body()?.student?.let {
                        data.postValue(response.body())
                        error.postValue(false)
                    }
                }

                override fun onFailure(call: Call<StudentsResponseDto>, t: Throwable) {
                    Timber.d("${t.localizedMessage}")
                    error.postValue(true)
                }
            })
    }

    fun postStudentData(
        username: String,
        password: String,
        university: String,
        data: MutableLiveData<StudentsResponseDto>,
        error: MutableLiveData<Boolean>
    ) {
        try {
            val userAgent = UserAgentGenerator.getRandomAgent()

            val response: Connection.Response = Jsoup.connect(STUDENTS_URL)
                .method(Connection.Method.GET)
                .userAgent(userAgent)
                .execute()

            val loginForm = LoginForm(
                username = username,
                password = password,
                cookies = response.cookies()
            )

            return api.postStudentData(university, loginForm)
                .enqueue(object : Callback<StudentsResponseDto> {
                    override fun onResponse(
                        call: Call<StudentsResponseDto>,
                        response: Response<StudentsResponseDto>
                    ) {
                        response.body()?.student?.let {
                            data.postValue(response.body())
                            error.postValue(false)
                        }
                    }

                    override fun onFailure(call: Call<StudentsResponseDto>, t: Throwable) {
                        Timber.d("${t.localizedMessage}")
                        error.postValue(true)
                    }
                })

        } catch (e: Exception) {
            Timber.d("e => $e")
        }
    }
}