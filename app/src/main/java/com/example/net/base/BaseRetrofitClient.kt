package com.example.net.base

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by CC
 * On 2021/11/23.
 */
abstract class BaseRetrofitClient {
    companion object CLIENT {
        private const val TIME_OUT = 30
    }

    private val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        handleBuilder(builder)
        builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLogger()).setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("OkHttp", message)
        }
    }

    abstract fun handleBuilder(builder: OkHttpClient.Builder)

    open fun <Service> getService(serviceClass: Class<Service>, baseUrl: String): Service {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
    }

}