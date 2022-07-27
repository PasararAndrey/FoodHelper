package com.example.foodhelper.data.calladapter

import com.example.foodhelper.data.util.ApiResult
import com.example.foodhelper.data.util.ApiResult.*
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ResultCall<T : Any>(
    private val call: Call<T>,
) : Call<ApiResult<T>> {
    override fun enqueue(callback: Callback<ApiResult<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                val result: ApiResult<T> = if (response.isSuccessful && body != null) {
                    SuccessResult(body)
                } else {
                    ErrorResult(code = response.code(), message = response.message())
                }
                callback.onResponse(this@ResultCall, Response.success(response.code(), result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result: ApiResult<T> = if (t is HttpException) {
                    ErrorResult(code = t.code(), message = t.message())
                } else {
                    ExceptionResult(t)
                }
                callback.onResponse(this@ResultCall, Response.success(result))
            }

        })
    }

    override fun clone(): Call<ApiResult<T>> {
        return ResultCall(call.clone())
    }

    override fun execute(): Response<ApiResult<T>> = throw NotImplementedError()

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()

}
