package com.xiyu.activity;

import android.os.Bundle;
import android.view.View;

import com.learn.core.navigation.NavigationBar;
import com.learn.core.statusbar.StatusBarUtil;
import com.xiyu.R;
import com.xiyu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.nav_bar)
    NavigationBar navigationBar;

    @OnClick({R.id.register_btn})
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.register_btn:
                startActivity(RegisterSuccessActivity.class);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        setStatusBarDark();
        navigationBar.setTitleSize(18.5f);
        navigationBar.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
