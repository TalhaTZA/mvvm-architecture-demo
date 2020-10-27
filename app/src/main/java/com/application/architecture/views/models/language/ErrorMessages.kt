package com.application.architecture.views.models.language


import com.google.gson.annotations.SerializedName

data class ErrorMessages(
    @SerializedName("internet")
    val internet: String,
    @SerializedName("internal")
    val internal: String
)