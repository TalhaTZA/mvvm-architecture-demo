package com.application.architecture.views.models.language


import com.application.architecture.views.models.language.ErrorMessages
import com.google.gson.annotations.SerializedName

 data class Messages(
    @SerializedName("error_messages")
    val errorMessages: ErrorMessages
)