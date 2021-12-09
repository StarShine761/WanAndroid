package com.example.wanandroid.bean

import androidx.room.*


/**
 * Created by CC
 * On 2021/12/2.
 */
@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg users: User)



    @Delete
    fun delete(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: User)

}