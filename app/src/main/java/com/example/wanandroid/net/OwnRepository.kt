package com.example.wanandroid.net

import com.example.net.base.BaseRepository
import com.example.net.entity.ApiResponse
import com.example.wanandroid.bean.User
import com.example.wanandroid.bean.WxArticle

/**
 * Created by CC
 * On 2021/11/24.
 */
class OwnRepository : BaseRepository() {
    private val mService by lazy {
        RetrofitClient.service
    }

    suspend fun login(username: String, password: String): ApiResponse<User?> {
        return executeHttp { mService.login(username, password) }

    }
   suspend fun list(page:Int): ApiResponse<WxArticle> {
        return executeHttp { mService.list(page) }

    }
}