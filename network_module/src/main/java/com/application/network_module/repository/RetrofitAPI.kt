package com.application.network_module.repository


import com.application.network_module.models.response.ResponseGeneral
import retrofit2.Response
import retrofit2.http.*

internal interface RetrofitAPI {

    companion object {
        const val HEADER_POSTFIX = ": "
        const val HEADER_TAG = "@"
        const val HEADER_TAG_PUBLIC = "public"
    }

    /*POST REQUEST*/


    /*GET REQUEST*/

    @GET("todos")
    suspend fun getTodos(): Response<ResponseGeneral<Any>>


    /*PUT REQUEST*/


    /*DELETE*/


}