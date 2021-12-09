package com.example.wanandroid.vm

import com.example.base.base.BaseViewModel
import com.example.net.entity.ApiResponse
import com.example.wanandroid.bean.User
import com.example.wanandroid.bean.WxArticle
import com.example.wanandroid.net.OwnRepository

/**
 * Created by CC
 * On 2021/11/24.
 */
class ApiViewModel : BaseViewModel() {
    private val repository by lazy { OwnRepository() }

    suspend fun login(username: String, password: String): ApiResponse<User?> {
        return repository.login(username, password)
    }
    suspend fun list(): ApiResponse<List<WxArticle>?> {
        return repository.list()
    }

}