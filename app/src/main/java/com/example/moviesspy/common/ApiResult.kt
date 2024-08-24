package com.example.moviesspy.common

sealed interface ApiResult<out T> {

    data class Success<out T>(val data: T): ApiResult<T>

    data class Error<out T>(val message: String): ApiResult<T>
}