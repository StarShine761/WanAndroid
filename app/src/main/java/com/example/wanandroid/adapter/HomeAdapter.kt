package com.example.wanandroid.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.wanandroid.R
import com.example.wanandroid.bean.MyData

/**
 * Created by CC
 * On 2021/12/13.
 */
class HomeAdapter(list: MutableList<MyData>) :
    BaseQuickAdapter<MyData, BaseViewHolder>(
        R.layout.item_home, list
    ), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: MyData) {
        item.let {
            holder.setText(R.id.wx_id, item.chapterName)
                .setText(R.id.wx_name, item.id.toString())
        }
    }
}