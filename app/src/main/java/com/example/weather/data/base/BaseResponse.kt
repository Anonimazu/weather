package com.example.weather.data.base

sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val message: String, val cause: Exception? = null) : BaseResponse<Nothing>()
    data object Loading : BaseResponse<Nothing>()
}