package com.application.architecture.views.models.language


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Global(
    @SerializedName("string_app_name")
    @Expose
    val stringAppName: String = ""
)