package com.stathis.unipiapp.network.students

import com.stathis.unipiapp.models.grading.LoginForm
import com.stathis.unipiapp.models.grading.StudentsResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface StudentsEndpoints {

    @POST("student/{university}")
    suspend fun postStudentData(
        @Path("university") university : String,
        @Body loginForm: LoginForm
    ) : Response<StudentsResponseDto>
}