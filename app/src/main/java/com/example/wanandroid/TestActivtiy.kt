package com.example.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_test_activtiy.*
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TestActivtiy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_activtiy)
        withcontext()
        async()
        async_await()
        doasync()

    }

    fun withcontext() {
        withcontext.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val time1 = System.currentTimeMillis()
                Log.e("TAG", "进入协程")

                val task1 = withContext(Dispatchers.IO) {
                    delay(2000)
                    Log.e("TAG", "1.执行task1.... [当前线程为：${Thread.currentThread().name}]")
                    "one"  //返回结果赋值给task1


                }
                Log.e("TAG", "协程1完成")


                val task2 = withContext(Dispatchers.IO) {
                    delay(1000)
                    Log.e("TAG", "2.执行task2.... [当前线程为：${Thread.currentThread().name}]")
                    "two"  //返回结果赋值给task2
                }
                Log.e("TAG", "协程2完成")

                withcontext.text = "协程2完成"

                Log.e(
                    "TAG",
                    "task1 = $task1  , task2 = $task2 , 耗时 ${System.currentTimeMillis() - time1} ms  [当前线程为：${Thread.currentThread().name}]"
                )

            }
        }
    }

    fun async() {
        async.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val time1 = System.currentTimeMillis()
                Log.e("TAG", "进入协程")

                val task1 = async(Dispatchers.IO) {
                    delay(2000)
                    Log.e("TAG", "1.执行task1.... [当前线程为：${Thread.currentThread().name}]")
                    "one"  //返回结果赋值给task1


                }
                Log.e("TAG", "协程1完成")


                val task2 = async(Dispatchers.IO) {
                    delay(1000)
                    Log.e("TAG", "2.执行task2.... [当前线程为：${Thread.currentThread().name}]")
                    "two"  //返回结果赋值给task2
                }
                Log.e("TAG", "协程2完成")

                withcontext.text = "协程2完成"

                Log.e(
                    "TAG",
                    "task1 = $task1  , task2 = $task2 , 耗时 ${System.currentTimeMillis() - time1} ms  [当前线程为：${Thread.currentThread().name}]"
                )

            }


        }


    }


    fun async_await() {
        async_await.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val time1 = System.currentTimeMillis()
                Log.e("TAG", "进入协程")

                val task1 = async(Dispatchers.IO) {
                    delay(2000)
                    Log.e("TAG", "1.执行task1.... [当前线程为：${Thread.currentThread().name}]")
                    "one"  //返回结果赋值给task1


                }.await()
                Log.e("TAG", "协程1完成")


                val task2 = async(Dispatchers.IO) {
                    delay(1000)
                    Log.e("TAG", "2.执行task2.... [当前线程为：${Thread.currentThread().name}]")
                    "two"  //返回结果赋值给task2
                }.await()
                Log.e("TAG", "协程2完成")

                withcontext.text = "协程2完成"

                Log.e(
                    "TAG",
                    "task1 = $task1  , task2 = $task2 , 耗时 ${System.currentTimeMillis() - time1} ms  [当前线程为：${Thread.currentThread().name}]"
                )

            }


        }

    }


    fun doasync(){
        doasync.setOnClickListener {
            doAsync{


                Log.e("TAG", " doAsync...   [当前线程为：${Thread.currentThread().name}]")
                uiThread {
                    Log.e("TAG", " uiThread....   [当前线程为：${Thread.currentThread().name}]")

                }
            }
        }
    }
}