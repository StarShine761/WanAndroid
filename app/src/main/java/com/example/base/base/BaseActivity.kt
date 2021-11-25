package com.example.base.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.base.anno.ActivityConfiguration
import com.example.base.util.toast
import com.example.net.SHOW_TOAST
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by CC
 * On 2021/11/24.
 */
abstract class BaseActivity(@LayoutRes contentLayoutId: Int) : AppCompatActivity(contentLayoutId),
    IUiView {
    private var useEventBus = false

    init {
        //let扩展函数的实际上是一个作用域函数，当你需要去定义一个变量在一个特定的作用域范围内，
        // let函数的是一个不错的选择；let函数另一个作用就是可以避免写一些判断null的操作。

        //场景一: 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理。
        //场景二: 然后就是需要去明确一个变量所处特定的作用域范围内可以使用
        this.javaClass.getAnnotation(ActivityConfiguration::class.java)?.let {
            useEventBus = it.useEventBus
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeUi()
    }

    protected abstract fun init()

    private fun observeUi() {
        LiveEventBus.get<String>(SHOW_TOAST).observe(this) {
            toast(it)
        }
    }

    private var progressDialog: ProgressDialog? = null

    override fun showLoading() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.takeIf { it.isShowing }?.dismiss()
    }


}