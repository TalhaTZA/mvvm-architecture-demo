package com.application.network_module.repository

import android.content.Context
import android.util.Log
import com.application.network_module.NetworkModule
import com.application.network_module.utils.Enums
import com.application.network_module.utils.TinyDB
import com.application.newtork_module.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

internal object RetrofitBuilder {

    private val retrofitHashMap = HashMap<String, RetrofitAPI>()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun getRetrofitInstance(url: Enums.RetrofitBaseUrl): RetrofitAPI {

        val baseUrl = url.baseUrl

        if (!retrofitHashMap.containsKey(baseUrl) ||
            retrofitHashMap[baseUrl] == null
        ) {

            synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(
                        getOkHttpClient(
                            NetworkModule.context,
                            enableNetworkInterceptor(baseUrl)
                        )
                    )

                val restAPI = retrofit.build().create<RetrofitAPI>(RetrofitAPI::class.java)

                retrofitHashMap[baseUrl] = restAPI

                return restAPI
            }
        }

        return retrofitHashMap[baseUrl]!!
    }

    private fun enableNetworkInterceptor(baseUrl: String): Boolean {
        return baseUrl == Enums.RetrofitBaseUrl.BASE_URL.baseUrl
    }

    private fun getOkHttpClient(context: Context, isBescure: Boolean): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = Constants.LOG_LEVEL_API
        }

        val builder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(ChuckInterceptor(context))
            .connectTimeout(Constants.API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.API_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.API_WRITE_TIMEOUT, TimeUnit.SECONDS)

        if (isBescure)
            builder.addNetworkInterceptor(NetworkInterceptorBSecure(context))

        return builder.build()
    }

    private class NetworkInterceptorBSecure(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {

            val original = chain.request()


            val tinyDB = TinyDB.instance

            val locale =
                tinyDB.getString(Enums.TinyDBKeys.LOCALE.key).let { if (it.isEmpty()) "en" else it }

            val token = tinyDB.getString(
                Enums.TinyDBKeys.TOKEN_USER.key
            )

            val headerTag = original.header(RetrofitAPI.HEADER_TAG)

            Log.e("Layer", "Network Layer [ACCESS Token: $token \nUrl: ${original.url}]")

            val builder = original.newBuilder()

            if (headerTag == null && !token.equals(
                    "",
                    ignoreCase = true
                )
            ) {
                builder.addHeader("Authorization", "Bearer $token")
            }

            val request = builder
                .addHeader("x-device-type", "Android")
                .removeHeader(RetrofitAPI.HEADER_TAG)
                .method(original.method, original.body)
                .build()

            val response = chain.proceed(request)

            if (response.isSuccessful) {

//                val obj =
//                    Gson().fromJson(response.peekBody(100000).string(), ResponseGeneral::class.java)
//
//                if (obj.update_available == 1) {
//
//                    val res = response.newBuilder().code(400)
//                        .build()
//
//                    ApplicationClass.application.applicationContext.sendBroadcast(
//                        Intent().apply {
//                            putExtra(Constants.INTENT_PAYLOAD_KEY, res.code)
//                            action = Constants.BROADCAST_NAME_RESPONSE
//                        }
//                    )
//
//                    return res
//                }

            } else if (!response.isSuccessful) {

//                ApplicationClass.application.applicationContext.sendBroadcast(
//                    Intent().apply {
//                        putExtra(Constants.INTENT_PAYLOAD_KEY, response.code)
//                        action = Constants.BROADCAST_NAME_RESPONSE
//                    }
//                )

//                EventBus.getDefault().post(response)
            }

            return response
        }

    }

}