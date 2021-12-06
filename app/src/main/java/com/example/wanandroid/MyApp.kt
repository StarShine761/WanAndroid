package com.example.wanandroid

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.base.base.BaseApp

/**
 * Created by CC
 * On 2021/11/24.
 */
class MyApp : BaseApp() {

    companion object {
        var context: Application? = null
        fun getAppContext(): Context {
            return context!!
        }

    }


    override fun onCreate() {
        super.onCreate()
        context = this
    }

}