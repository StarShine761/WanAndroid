package com.example.wanandroid.bean

/**
 * Created by CC
 * On 2021/12/8.
 */
data class WxArticle(
    var curPage: Int,
    var datas: List<MyData>,


    )

data class MyData(
    var chapterName: String,
    var superChapterName: String,
    var id: Int


)
