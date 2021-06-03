package com.application.architecture.views.utils

object Enums {
    enum class TinyDBKeys(val key: String) {
        LOCALE("locale"),
        TOKEN_USER("auth_token_user")
    }

    enum class NavigationType{
        ACTION,
        ID
    }
}