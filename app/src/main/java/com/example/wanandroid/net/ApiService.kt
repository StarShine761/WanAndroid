package com.example.wanandroid.net

import com.example.net.entity.ApiResponse
import com.example.wanandroid.bean.User
import com.example.wanandroid.bean.WxArticle
import retrofit2.http.*


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

    @GET("article/listproject/{page}/json?page_size=40")
    suspend fun list(@Path("page")  page:Int): ApiResponse<WxArticle>


}