package com.example.net.entity

import java.io.Serializable

/**
 * Created by CC
 * On 2021/11/23.
 */
//在Kotlin开发中类和方法默认不允许被继承和重写，等同于Java中用 final 修饰类和方法。
//如果在Kotlin 中类和方法想被继承和重写，需添加open 关键字修饰
open class ApiResponse<T>(
    open val data: T? = null,
    open val errorCode: Int? = null,
    open val errorMsg: String? = null,
    open val error: Throwable? = null,
) : Serializable {
    val isSuccess: Boolean
        get() = errorCode == 0

}

//data class算是Kotlin中一大闪光点，data class就是一个类中只包含一些数据字段，类似于vo,pojo,java bean。
//一般而言，我们在Java中定义了这个数据类之后要重写一下toString，equals等方法。要生成get,set方法。
//然而在Kotlin中这些都不在需要自己手动去敲了，编译器在背后默默给我们生成了如下的东西
data class ApiSuccessResponse<T>(val response: T) : ApiResponse<T>(data = response)

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiFailedResponse<T>(override val errorCode: Int?, override val errorMsg: String?) :
    ApiResponse<T>(errorCode = errorCode, errorMsg = errorMsg)

data class ApiErrorResponse<T>(val throwable: Throwable) : ApiResponse<T>(error = throwable)
