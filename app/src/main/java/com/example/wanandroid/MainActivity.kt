package com.example.wanandroid

import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.base.base.BaseActivity
import com.example.wanandroid.vm.ApiViewModel
import com.example.wanandroid.vm.ItemViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(R.layout.activity_main) {
    private val mViewModel by viewModels<ApiViewModel>()
    private val viewModel: ItemViewModel by viewModels()


    override fun init() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
    }


}