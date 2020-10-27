package com.application.architecture.views.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.architecture.views.utils.TinyDB
import kotlinx.coroutines.*

internal open class BaseViewModel : ViewModel() {

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

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}