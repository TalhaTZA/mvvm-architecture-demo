package com.application.architecture.views.models.language


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LanguageJson(
    @SerializedName("global")
    @Expose
    val global: Global
)