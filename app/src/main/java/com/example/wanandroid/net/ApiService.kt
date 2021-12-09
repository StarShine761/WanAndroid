package com.example.wanandroid.net

import com.example.net.entity.ApiResponse
import com.example.wanandroid.bean.User
import com.example.wanandroid.bean.WxArticle
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }


    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): ApiResponse<User?>
    @GET("wxarticle/chapters/json")
    suspend fun list(): ApiResponse<List<WxArticle>?>


}