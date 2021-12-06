package com.example.wanandroid.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "admin") val admin: String?,
    @ColumnInfo(name = "coin_count") val coinCount: Int,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "nickname") val nickname: String?,
    @ColumnInfo(name = "token") val token: String?

)