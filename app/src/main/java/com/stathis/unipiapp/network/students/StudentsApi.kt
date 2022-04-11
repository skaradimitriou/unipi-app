package com.stathis.unipiapp.network.students

import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.models.Result
import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.util.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jsoup.Connection
import org.jsoup.Jsoup
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

object StudentsApi {

    val logger = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(logger).build()

    private val api = Retrofit.Builder()
        .baseUrl(STUDENTS_API_BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StudentsEndpoints::class.java)

    suspend fun loginGuestUser(
        data: MutableLiveData<Result<StudentsResponseDto>>
    ) {

        val loginForm = LoginForm(
            username = GUEST,
            password = GUEST,
            cookies = null
        )

        val apiCall = api.postStudentData(GUEST, loginForm)
        Timber.d("RESPONSE => $apiCall")

        if (apiCall.code() == 200) {
            // call was successful
            data.setData(apiCall.body())
        } else {
            data.setError(apiCall.message())
        }
    }

    suspend fun postStudentData(
        username: String,
        password: String,
        university: String,
        data: MutableLiveData<Result<StudentsResponseDto>>
    ) {
        try {
            val userAgent = UserAgentGenerator.getRandomAgent()

            val response: Connection.Response = Jsoup.connect(STUDENTS_URL)
                .method(Connection.Method.GET)
                .userAgent(userAgent)
                .timeout(60 * 1000)
                .execute()

            val loginForm = LoginForm(
                username = username,
                password = password,
                cookies = response.cookies()
            )

            val apiCall = api.postStudentData(university, loginForm)

            Timber.d("RESPONSE => $apiCall")

            if (apiCall.code() == 200) {
                // call was successful
                data.setData(apiCall.body())
            } else {
                data.setError(apiCall.message())
            }

        } catch (e: Exception) {
            Timber.d("e => $e")
            data.postValue(Result.Error(error = e.localizedMessage))
        }
    }
}