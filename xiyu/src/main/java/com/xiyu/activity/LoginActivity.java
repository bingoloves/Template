package com.xiyu.activity;

import android.os.Bundle;
import android.view.View;

import com.xiyu.R;
import com.xiyu.base.BaseActivity;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @OnClick({R.id.tv_register,R.id.login_btn})
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.login_btn:
                startActivity(MainActivity.class,true);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setFullScreen();
    }

}
