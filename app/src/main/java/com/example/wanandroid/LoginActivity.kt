package com.example.wanandroid

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.amitshekhar.utils.Utils
import com.example.base.base.BaseActivity
import com.example.base.util.launchWithLoadingAndCollect
import com.example.net.toast
import com.example.wanandroid.bean.User
import com.example.wanandroid.vm.ApiViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.*


// At the top level of your kotlin file:

class LoginActivity : BaseActivity(R.layout.activity_login) {

    val TAG = LoginActivity::class.simpleName
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
        name = et_username.text.toString()
        pwd = et_password.text.toString()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mm"
        ).build()

        launchWithLoadingAndCollect({
            Log.e(TAG, "第一次")
            mViewModel.login(name, pwd)

        }) {
            onSuccess = {
                lifecycleScope.launch(Dispatchers.IO) {
//                    db.userDao().insertAll(it!!)
//                    val user=db.userDao().getUser(1)

                    withContext(Dispatchers.Main) {
//                        button.text =user?.nickname
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    }

                }

            }
            onFailed = { errorCode, errorMsg ->
                toast("errorCode: $errorCode   errorMsg: $errorMsg")
            }
        }
//        //取消网络请求
//        lifecycleScope.cancel()
////        mViewModel.viewModelScope.coroutineContext.job.cancel()
//        Log.e(TAG,"取消")

//        launchWithLoadingAndCollect({
//            Log.e(TAG,"第二次")
//
//            mViewModel.login(name, pwd)
//
//        }) {
//            onSuccess = {
//                lifecycleScope.launch(Dispatchers.IO) {
////                    db.userDao().insertAll(it!!)
////                    val user=db.userDao().getUser(1)
//
//                    withContext(Dispatchers.Main){
////                        button.text =user?.nickname
//                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
//                    }
//
//                }
//
//            }
//            onFailed = { errorCode, errorMsg ->
//                toast("errorCode: $errorCode   errorMsg: $errorMsg")
//            }
//        }


    }
}
