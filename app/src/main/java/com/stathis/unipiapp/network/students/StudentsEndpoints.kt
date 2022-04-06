package com.stathis.unipiapp.network.students

import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentsEndpoints {

    @POST("student/{university}")
    fun postStudentData(
        @Path("university") university : String,
        @Body loginForm: LoginForm
    ) : Call<StudentsResponseDto>

    @POST("mock/student/{university}")
    fun postMockData(
        @Path("university") university : String,
    ) : Call<String>
}