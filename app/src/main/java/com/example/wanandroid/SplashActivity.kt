package com.example.wanandroid

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {
    companion object {
        private val TAG = SplashActivity::class.java.simpleName
    }

    // 要申请的权限
    private val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            val i = ContextCompat.checkSelfPermission(applicationContext, permissions[0])
            val l = ContextCompat.checkSelfPermission(applicationContext, permissions[1])
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED || l != PackageManager.PERMISSION_GRANTED) {
                startRequestPermission()
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    /**
     * 开始提交请求权限
     */
    private fun startRequestPermission() {
        Log.e(TAG, "请求权限")
        ActivityCompat.requestPermissions(this, permissions, 321)
    }

    @SuppressLint("MissingSuperCall")
    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        val flag = false
        val count = 0
        var zong = 0
        Log.e(TAG, "gran.leagth:" + permissions.size)
        for (i in permissions.indices) {
            Log.e(TAG, "gran:" + grantResults[i])
            if (PackageManager.PERMISSION_GRANTED == grantResults[i]) {
                zong++
                //已经被授权的
            } else {
            }
        }
        Log.e(TAG, "gran.leagth:" + permissions.size)
        Log.e(TAG, "zong:$zong")
        if (permissions.size - zong == 0) {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        } else {
            startRequestPermission()
        }
    }


}