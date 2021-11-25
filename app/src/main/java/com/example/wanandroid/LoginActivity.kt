package com.example.wanandroid

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.base.BaseActivity
import com.example.base.util.launchWithLoadingAndCollect
import com.example.net.toast
import com.example.wanandroid.bean.User
import com.example.wanandroid.vm.ApiViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class LoginActivity : BaseActivity(R.layout.activity_login) {
    var name: String = ""
    var pwd: String = ""
    private val mViewModel by viewModels<ApiViewModel>()

    //    private val mBinding by viewBinding()
    override fun init() {
        button.setOnClickListener {
            login()


        }
    }

    private fun login() {
        name = et_password.text.toString()
        pwd = et_password.text.toString()

        launchWithLoadingAndCollect({
            mViewModel.login(name, pwd)
        }) {
            onSuccess = {
//                suspend fun incrementCounter() {
//
//                    dataStore.edit { settings ->
//                        // 可以安全地增加我们的计数器，而不会因为资源竞争而丢失数据。
//                        val currentCounterValue = settings[USER_DATA] ?: 0
//                        settings[USER_DATA] = it
//                    }
//                }

//                mBinding.tvContent.text = it.toString()
                button.text = it?.username
            }
            onFailed = { errorCode, errorMsg ->
                toast("errorCode: $errorCode   errorMsg: $errorMsg")
            }
        }
    }

}