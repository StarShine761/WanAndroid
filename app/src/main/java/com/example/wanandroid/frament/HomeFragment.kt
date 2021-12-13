package com.example.wanandroid.frament

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.base.BaseFragment
import com.example.base.util.launchWithLoadingAndCollect
import com.example.net.toast
import com.example.wanandroid.R
import com.example.wanandroid.adapter.HomeAdapter
import com.example.wanandroid.bean.MyData
import com.example.wanandroid.bean.WxArticle
import com.example.wanandroid.vm.ApiViewModel
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var page = 1
    private var list = ArrayList<MyData>()
    private val mViewModel by viewModels<ApiViewModel>()
    private var adapter: HomeAdapter? = null

    private var more=true;

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        val layoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = layoutManager

        adapter = HomeAdapter(list)
        recyclerview.adapter = adapter
        adapter?.loadMoreModule?.setOnLoadMoreListener {
            if(more){
                getData()
                adapter!!.loadMoreModule.loadMoreComplete()

            }else{
                adapter!!.loadMoreModule.loadMoreEnd()

            }
        }
        getData()


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        launchWithLoadingAndCollect({
            mViewModel.list(page)

        }) {
            onSuccess = {

                list.addAll(it!!.datas)
                page += 1
                if (page< it.curPage){
                    more=false
                }
                adapter?.notifyDataSetChanged()
            }
            onFailed = { errorCode, errorMsg ->
                Log.e("errorMsg",errorMsg.toString())
                toast("errorCode: $errorCode   errorMsg: $errorMsg")

            }
        }
    }

}