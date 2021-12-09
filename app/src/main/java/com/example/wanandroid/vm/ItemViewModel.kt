package com.example.wanandroid.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanandroid.bean.WxArticle

/**
 * Created by CC
 * On 2021/12/9.
 */
class ItemViewModel : ViewModel() {

    private val mutableSelectedItem = MutableLiveData<List<WxArticle>>()
    val selectedItem: LiveData<List<WxArticle>> get() = mutableSelectedItem

    fun selectItem(item: List<WxArticle>) {
        mutableSelectedItem.value = item
    }
}