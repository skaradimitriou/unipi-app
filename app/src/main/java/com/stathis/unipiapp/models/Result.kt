package com.stathis.unipiapp.models

sealed class Result<T>(
    var data: T? = null,
    var error: String? = null
) {
    class Success<T>(data: T) : Result<T>(data = data)
    class Error<T>(error: String) : Result<T>(error = error)
}
