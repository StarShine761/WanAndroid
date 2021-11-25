package com.example.wanandroid.net

import com.example.net.base.BaseRetrofitClient
import okhttp3.OkHttpClient

/**
 * Created by CC
 * On 2021/11/24.
 */
object RetrofitClient : BaseRetrofitClient() {
    override fun handleBuilder(builder: OkHttpClient.Builder) = Unit

    val service by lazy { getService(ApiService::class.java, ApiService.BASE_URL) }
}