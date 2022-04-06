package com.stathis.unipiapp.models.grading

data class LoginForm (
    val username: String,
    val password: String,
    val cookies : Map<String, String>?
)
