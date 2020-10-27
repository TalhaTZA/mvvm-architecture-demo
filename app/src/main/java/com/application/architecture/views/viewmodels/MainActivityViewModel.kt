package com.application.architecture.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.application.architecture.views.ApplicationClass
import com.application.architecture.views.models.helper.NotificationMessage
import com.application.architecture.views.utils.DisplayNotification
import com.application.network_module.models.response.ResponseGeneral
import com.application.network_module.repository.ApiRepository
import com.application.network_module.repository.onError
import com.application.network_module.repository.onSuccess
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class MainActivityViewModel : BaseViewModel() {

    /*DATA SECTION*/


    /*API SECTION*/

    fun callServerFor() {

        coroutineScope.launch {

            toggleLoader(true)

            val data = ApiRepository.callApi()

            data.onSuccess {
                if (it.isSuccessful) {
                    // set data to live data
                } else {
                    handleServerError(it.errorBody())
                }

            }.onError {
                showErrorMessage(it.exception)
            }


            toggleLoader(false)

        }

    }


    public override suspend fun toggleLoader(flag: Boolean) {
        super.toggleLoader(flag)
    }


}