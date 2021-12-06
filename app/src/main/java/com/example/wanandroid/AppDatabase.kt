package com.example.wanandroid

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wanandroid.bean.User
import com.example.wanandroid.bean.UserDao


/**
 * Created by CC
 * On 2021/12/2.
 */
@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}