package com.example.net.observer

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.net.entity.*
import com.example.net.toast

/**
 * Created by CC
 * On 2021/11/23.
 */
typealias StateLiveData<T> = LiveData<ApiResponse<T>>
typealias StateMutableLiveData<T> = MutableLiveData<ApiResponse<T>>

@MainThread
inline fun <T> StateMutableLiveData<T>.observeState(
    owner: LifecycleOwner,
    listenerBuilder: ResultBuilder<T>.() -> Unit
) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    observe(owner) { apiResponse ->
        when (apiResponse) {
            is ApiSuccessResponse -> listener.onSuccess(apiResponse.response)
            is ApiEmptyResponse -> listener.onDataEmpty()
            is ApiFailedResponse -> listener.onFailed(apiResponse.errorCode, apiResponse.errorMsg)
            is ApiErrorResponse -> listener.onError(apiResponse.throwable)
        }
        listener.onComplete()
    }
}

@MainThread
inline fun <T> LiveData<ApiResponse<T>>.observeState(
    owner: LifecycleOwner,
    listenerBuilder: ResultBuilder<T>.() -> Unit
) {
    val listener = ResultBuilder<T>().also(listenerBuilder)
    observe(owner) { apiResponse ->
        when (apiResponse) {
            is ApiSuccessResponse -> listener.onSuccess(apiResponse.response)
            is ApiEmptyResponse -> listener.onDataEmpty()
            is ApiFailedResponse -> listener.onFailed(apiResponse.errorCode, apiResponse.errorMsg)
            is ApiErrorResponse -> listener.onError(apiResponse.throwable)
        }
        listener.onComplete()
    }
}

class ResultBuilder<T> {
    var onSuccess: (data: T?) -> Unit = {}
    var onDataEmpty: () -> Unit = {}
    var onFailed: (errorCode: Int?, errorMsg: String?) -> Unit = { _, errorMsg ->
        errorMsg?.let { toast(it) }
    }
    var onError: (e: Throwable) -> Unit = { e ->
        e.message?.let { toast(it) }
    }
    var onComplete: () -> Unit = {}
}