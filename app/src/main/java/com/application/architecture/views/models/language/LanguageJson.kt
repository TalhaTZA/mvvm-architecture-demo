package com.application.architecture.views.models.language


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LanguageJson(
    @SerializedName("messages")
    @Expose
    val messages: Messages,
    @SerializedName("global")
    @Expose
    val global: Global
)