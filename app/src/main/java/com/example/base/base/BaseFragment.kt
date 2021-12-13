package com.example.base.base

import android.app.ProgressDialog
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.base.anno.FragmentConfiguration


/**
 * Created by CC
 * On 2021/11/23.
 */
abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    private var useEventBus = false

    init {
        this.javaClass.getAnnotation(FragmentConfiguration::class.java)?.let {
            useEventBus = it.useEventBus
        }
    }
    private var progressDialog: ProgressDialog? = null

    fun showLoading() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(activity)
        progressDialog?.show()
    }

    fun dismissLoading() {
        progressDialog?.takeIf { it.isShowing }?.dismiss()
    }
}