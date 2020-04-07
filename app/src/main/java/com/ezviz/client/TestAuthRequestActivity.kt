package com.ezviz.client

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ezviz.demo1.R
import com.ezviz.sdk.auth.EzvizAuthSDK
import com.ezviz.sdk.auth.api.EaAuthRequest
import com.ezviz.sdk.auth.api.EaResultListener
import kotlinx.android.synthetic.main.activity_auth_request.*

class TestAuthRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_request)
        // 打开日志开关
        EzvizAuthSDK.showSdkLog(true)
        // 在发起授权必须先初始化SDK
        EzvizAuthSDK.init(applicationContext)
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        updateAuthCode()
        updateAuthUrl()
    }

    private fun updateAuthUrl() {
        tv_current_auth_url.text = "authUrl: $AUTH_URL"
    }

    private fun updateAuthCode(){
        tv_last_auth_result.text = "authCode: $mLastestAuthCode"
    }

    override fun onDestroy() {
        super.onDestroy()
        EzvizAuthSDK.unInit()
    }

    fun onClickStartAuth(@Suppress("UNUSED_PARAMETER") view: View) {
        startAuth()
    }

    private fun startAuth() {
        // 构建授权请求
        val request = EaAuthRequest.Builder()
                // 从官网开发者后台获取
                // 开放平台官网：https://open.ys7.com/
                .setAppkey(APP_KEY)
                // 默认：https://open.ys7.com
                .setAuthUrl(AUTH_URL)
                // 需与后台预留一致
                // 默认：default，则授权过程不校验该参数
                .setRedirectUrl("default")
//                // 暂未启用
//                .setScope("default")
//                // 暂未启用
//                .setState("default")
                .build()
        // 设置只用web授权
//        EzvizAuthSDK.setOnlyUseWebAuth(true)
        // 发起授权请求
        EzvizAuthSDK.getInstance().startAuthByYingShiAccount(request, listener)
    }

    @SuppressLint("SetTextI18n")
    //
    private val listener = EaResultListener { resp ->
        Log.i("EzvizAuthSDK", "AuthCode is " + resp.authCode)
        mLastestAuthCode = resp.authCode
        runOnUiThread {
            updateAuthCode()
            if (resp != null && resp.resultCode == 0){
                toast("授权成功")
            }else{
                toast("授权失败！")
            }
        }
    }

    private fun toast(msg: String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }

    companion object{

        private var mLastestAuthCode: String? = null

        // 线上平台
        const val APP_KEY = "fdbdb7e075974a8a9e3006056a8153e4"
        const val AUTH_URL = "https://open.ys7.com"
        // pb平台
//        const val APP_KEY = "29c9626f94a64270b89dc79f3d570be2"
//        const val AUTH_URL = "https://pbopen.ys7.com"
        // test12平台
//        const val APP_KEY = "680948cc41c44fbaac23d8b47be4028b"
//        const val AUTH_URL = "http://test12open.ys7.com"

    }

}
