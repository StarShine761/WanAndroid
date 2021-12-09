package com.example.wanandroid.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "admin") var admin: String?,
    @ColumnInfo(name = "coin_count") var coinCount: Int,
    @ColumnInfo(name = "email") var email: String?,
    @ColumnInfo(name = "nickname") var nickname: String?,
    @ColumnInfo(name = "token") var token: String?

)