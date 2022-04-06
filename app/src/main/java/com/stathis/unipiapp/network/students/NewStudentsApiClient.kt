package com.stathis.unipiapp.network.students

import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.util.STUDENTS_URL
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import timber.log.Timber
import kotlin.collections.HashMap


class NewStudentsApiClient() {

    val userAgent ="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36"

    fun getData() {
        try {
            val response: Connection.Response = Jsoup.connect(STUDENTS_URL)
                .method(Connection.Method.GET)
                .userAgent(userAgent)
                .execute()

            //response.cookies()

            val loginForm = LoginForm(
                username = "guest",
                password = "guest!@",
                cookies = null
            )

            StudentsApi.postStudentData("GUEST",loginForm)

        } catch (e : Exception) {
            Timber.d("e => $e")
        }
    }
}