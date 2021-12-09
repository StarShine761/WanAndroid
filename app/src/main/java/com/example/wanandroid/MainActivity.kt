package com.example.wanandroid

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.RecyclerView
import com.example.base.base.BaseActivity
import com.example.base.util.launchWithLoadingAndCollect
import com.example.net.entity.ApiResponse
import com.example.net.toast
import com.example.wanandroid.bean.WxArticle
import com.example.wanandroid.frament.Home2Fragment
import com.example.wanandroid.frament.Home3Fragment
import com.example.wanandroid.frament.Home4Fragment
import com.example.wanandroid.frament.HomeFragment
import com.example.wanandroid.vm.ApiViewModel
import com.example.wanandroid.vm.ItemViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : BaseActivity(R.layout.activity_main) {
    private val mViewModel by viewModels<ApiViewModel>()
    private val viewModel: ItemViewModel by viewModels()


    override fun init() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        launchWithLoadingAndCollect({
            mViewModel.list()

        }) {
            onSuccess = {
                viewModel.selectItem(it!!)
            }
            onFailed = { errorCode, errorMsg ->
                toast("errorCode: $errorCode   errorMsg: $errorMsg")

            }
        }
    }


}