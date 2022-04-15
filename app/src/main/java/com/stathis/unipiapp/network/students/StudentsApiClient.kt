package com.stathis.unipiapp.network.students

import androidx.lifecycle.MutableLiveData
import com.stathis.unipiapp.di.student.DaggerStudentApiComponent
import com.stathis.unipiapp.models.Result
import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import com.stathis.unipiapp.util.*
import org.jsoup.Connection
import org.jsoup.Jsoup
import timber.log.Timber
import javax.inject.Inject

class StudentsApiClient {

    @Inject
    lateinit var api : StudentsEndpoints

    init {
        DaggerStudentApiComponent.create().inject(this)
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