package com.example.foodhelper.data.util

import com.example.foodhelper.data.util.ApiResult.*

sealed interface ApiResult<T : Any> {
    class SuccessResult<T : Any>(val data: T) : ApiResult<T>
    class ErrorResult<T : Any>(val code: Int, val message: String) : ApiResult<T>
    class ExceptionResult<T : Any>(val e: Throwable) : ApiResult<T>
}


suspend fun <T : Any> ApiResult<T>.onSuccess(
    executable: suspend (T) -> Unit,
): ApiResult<T> = apply {
    if (this is SuccessResult<T>) {
        executable(data)
    }
}

suspend fun <T : Any> ApiResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit,
): ApiResult<T> = apply {
    if (this is ErrorResult<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> ApiResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit,
): ApiResult<T> = apply {
    if (this is ExceptionResult<T>) {
        executable(e)
    }
}