package com.chetan.mvicomposecleanarch.base.common

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T?) : ApiResponse<T>(data = data)
    class Error<T>(message: String?, throwable: Throwable?) :
        ApiResponse<T>(message = message, throwable = throwable)

    class Loading<T> : ApiResponse<T>()
}