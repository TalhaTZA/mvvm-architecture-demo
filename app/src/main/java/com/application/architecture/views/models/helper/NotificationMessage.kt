package com.application.architecture.views.models.helper

import com.application.architecture.views.utils.DisplayNotification


internal data class NotificationMessage(
    var show: Boolean = true,
    var message: String = "",
    var style: DisplayNotification.STYLE = DisplayNotification.STYLE.INFO
)