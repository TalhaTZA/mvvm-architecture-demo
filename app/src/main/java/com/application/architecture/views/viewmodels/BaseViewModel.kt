package com.application.architecture.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.architecture.views.ApplicationClass
import com.application.architecture.views.models.helper.NotificationMessage
import com.application.architecture.views.utils.DisplayNotification
import com.application.architecture.views.utils.TinyDB
import com.application.network_module.models.response.ResponseGeneral
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    protected val tinyDB by lazy { TinyDB.instance }


    private val _loader = MutableLiveData<Boolean>()

    val loader: LiveData<Boolean>
        get() = _loader


    private fun setLoader(flag: Boolean) {
        _loader.value = flag
    }

    private val _navigate = MutableLiveData<Boolean>()

    val navigate: LiveData<Boolean>
        get() = _navigate

    fun setNavigate(flag: Boolean) {
        _navigate.value = flag
    }

    private val job = SupervisorJob()

    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }


    protected val coroutineScope = CoroutineScope(job + Dispatchers.IO + coroutineExceptionHandler)

    protected open suspend fun toggleLoader(flag: Boolean) {
        withContext(Dispatchers.Main) {

            if (_loader.value != flag) {
                setLoader(flag)
            }

        }
    }


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

    protected fun showErrorMessage(throwable: Throwable) {

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

    protected fun handleServerError(errorBody: ResponseBody?) {

        errorBody?.apply {
            val error = Gson().fromJson(
                this.string(),
                ResponseGeneral::class.java //CHANGE TO SPECIFIC ERROR RESPONSE
            )


            CoroutineScope(Dispatchers.Main).launch {
                if (error.message.isNotEmpty()) {
                    callMessageNotification(error.message.joinToString(" "))
                }
            }
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}