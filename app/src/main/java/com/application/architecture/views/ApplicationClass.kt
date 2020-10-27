package com.application.architecture.views

import android.app.Application
import android.content.Context
import com.application.architecture.views.models.language.LanguageJson
import com.application.architecture.views.utils.loadJSONFromAssets
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


internal class ApplicationClass : Application() {

    override fun onCreate() {

        super.onCreate()

        mApplicationClass = this

        setUpLanguageJson()


    }

    private fun setUpLanguageJson() {
        CoroutineScope(Dispatchers.IO).launch {
            languageJson =
                Gson().fromJson(loadJSONFromAssets("AppAndroidEn.json"), LanguageJson::class.java)
        }
    }



    companion object {

        @JvmStatic
        private lateinit var mApplicationClass: ApplicationClass

        @JvmStatic
        val application: ApplicationClass by lazy { mApplicationClass }


        @JvmStatic
        internal var languageJson: LanguageJson? = null
    }
}