package com.xiyu.activity;

import android.os.Bundle;
import android.view.View;

import com.xiyu.R;
import com.xiyu.base.BaseActivity;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @OnClick({R.id.tv_register})
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.tv_register:
                break;
            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

}
