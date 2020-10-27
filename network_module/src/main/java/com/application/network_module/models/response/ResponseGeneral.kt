package com.application.network_module.models.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ResponseGeneral<T>(
    @Json(name = "message")
    val message: List<String> = arrayListOf(),
    @Json(name = "status")
    val status_code: Int = 0,
    @Json(name = "body")
    var data: T,
    @Json(name = "exception")
    val exception: String? = null
)