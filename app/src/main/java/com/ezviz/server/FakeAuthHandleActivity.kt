package com.ezviz.server

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.ezviz.demo1.R
import com.ezviz.opensdk.oauth.EZOpenSDKOAuthManager

class FakeAuthHandleActivity : AppCompatActivity() {

    companion object{
        val TAG = FakeAuthHandleActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_auth_handle)
        handleReceivedData()
        finish()
    }

    private fun handleReceivedData() {
        var isReceivedData = false;
        intent?.data?.run {
            isReceivedData = true
            for (dataKey in queryParameterNames){
                Log.e(TAG, "key: $dataKey,value: ${getQueryParameters(dataKey)}")
            }
            // 利用sessionId打开萤石授权页面
            EZOpenSDKOAuthManager.openAuthPage(applicationContext, intent, "eyJhbGciOiJIUzM4NCJ9.eyJhdWQiOiIwNjQ4ZWRiM2M5YWI0ODE3YjI2NGIwNzIyYmJhYTA5YiIsImlzcyI6InlzYXV0aCIsImV4cCI6MTU4MzQxMDY1MiwiaWF0IjoxNTgzMzI0MjUyLCJzdWIiOiJzIiwiY3QiOiIzIiwidGQiOjE1ODMzMjQzMTI5MDUsInMiOiJjMTNiYzQ1ZjQ5NzIyZmJhMGVkYWUwMGM4ZjNiMDY2MiIsImNuIjoiUFJPIDYgUGx1cyJ9.cI3t-Qm5Xv8qhz1DGstU0mplEeLYhdl3xBpsgmatUkfXXyRKDQieq_3pnFcqgI6-")
        }
        if (!isReceivedData){
            Log.e(TAG, "not received any data!")
        }
    }
}
