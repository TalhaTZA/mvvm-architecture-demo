package com.application.architecture.views.viewmodels

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.architecture.views.ApplicationClass
import com.application.architecture.views.models.helper.NotificationMessage
import com.application.architecture.views.utils.DisplayNotification
import com.application.architecture.views.utils.TinyDB
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class MainActivityViewModel : BaseViewModel() {

    private val _notificationMessage = MutableLiveData<NotificationMessage>()

    val notificationMessage: LiveData<NotificationMessage>
        get() = _notificationMessage


    fun setNotificationMessage(message: NotificationMessage) {
        _notificationMessage.value = message
    }

    fun callMessageNotification(
        msg: String,
        style: DisplayNotification.STYLE = DisplayNotification.STYLE.FAILURE
    ) {
        try {
            CoroutineScope(Dispatchers.Main).launch {

                setNotificationMessage(
                    NotificationMessage(message = msg, style = style)
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun showErrorMessage(throwable: Throwable) {

        throwable.printStackTrace()

        when (throwable) {

            is SocketTimeoutException -> {
                callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internet ?: ""
                )
            }

            is UnknownHostException -> {
                callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internet ?: ""
                )
            }

            else -> {
                callMessageNotification(
                    ApplicationClass.languageJson?.messages?.errorMessages?.internal ?: ""
                )
            }
        }
    }

//    private fun handleServerError(errorString: String) {
//        val error = Gson().fromJson(
//            errorString,
//            ErrorResponseServer::class.java
//        )
//
//
//        CoroutineScope(Dispatchers.Main).launch {
//            if (error.message.isNotEmpty()) {
//                callMessageNotification(error.message.joinToString(" "))
//            }
//        }
//
//    }


    /*DATA SECTION*/


    /*API SECTION*/

    fun callServerFor() {

        coroutineScope.launch {

            toggleLoader(true)



            toggleLoader(false)

        }

    }


    public override suspend fun toggleLoader(flag: Boolean) {
        super.toggleLoader(flag)
    }


}