package com.example.wanandroid

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.base.base.BaseActivity
import com.example.base.util.launchWithLoadingAndCollect
import com.example.net.toast
import com.example.wanandroid.bean.User
import com.example.wanandroid.vm.ApiViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
            mViewModel.login(name, pwd)
        }) {
            onSuccess = {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.userDao().insertAll(it!!)
                    val user=db.userDao().getUser(1)

                    withContext(Dispatchers.Main){
                        button.text =user?.nickname
                    }

                }

            }
            onFailed = { errorCode, errorMsg ->
                toast("errorCode: $errorCode   errorMsg: $errorMsg")
            }
        }
    }
}
