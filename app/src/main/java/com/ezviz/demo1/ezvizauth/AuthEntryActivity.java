package com.ezviz.demo1.ezvizauth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ezviz.sdk.auth.common.AuthManager;

public class AuthEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 处理授权结果
        AuthManager.getInstance().handleAuthResponse(getIntent());
        // 关闭此Activity
        finish();
    }

}
