@file:Suppress("UNCHECKED_CAST")

package com.application.network_module.repository


import com.application.network_module.models.response.ResponseGeneral
import com.application.network_module.utils.Enums
import retrofit2.Response
import java.lang.Exception

object ApiRepository {

    private val api = RetrofitBuilder.getRetrofitInstance(Enums.RetrofitBaseUrl.BASE_URL)

    suspend fun callApi(): Result<Response<ResponseGeneral<Any>>> {

        return callApi{
            api.getTodos()
        } as Result<Response<ResponseGeneral<Any>>>
    }

    private suspend fun <T> callApi(api: suspend () -> Response<T>): Result<Any> {
        return try {
            Success(api.invoke())
        } catch (e: Exception) {
            Error(e)
        }
    }
}