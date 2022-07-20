package com.example.foodhelper.data.calladapter

import com.example.foodhelper.data.util.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkResultCallAdapter(
    private val resultType: Type,
) : CallAdapter<Type, Call<ApiResult<Type>>> {
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<ApiResult<Type>> {
        return ResultCall(call)
    }

}