package com.application.newtork_module.utils

import okhttp3.logging.HttpLoggingInterceptor


internal object Constants {

    var BASE_URL = "https://stage-base-url/"

    val LOG_LEVEL_API = HttpLoggingInterceptor.Level.BODY

    const val API_CONNECT_TIMEOUT: Long = 10

    const val API_READ_TIMEOUT: Long = 10

    const val API_WRITE_TIMEOUT: Long = 10

}
