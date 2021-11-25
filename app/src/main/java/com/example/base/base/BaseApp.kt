package com.example.base.base

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * Created by CC
 * On 2021/11/23.
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
    }

    companion object {
        lateinit var instance: BaseApp
            private set
    }

    private inner class ApplicationLifecycleObserver : LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private fun onAppForeground() {
            // TODO: "ApplicationObserver: app moved to foreground"
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        private fun onAppBackground() {
            // TODO: "ApplicationObserver: app moved to background"
        }
    }
}