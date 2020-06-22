package com.xiyu.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.learn.core.navigation.NavigationBar;
import com.learn.core.statusbar.StatusBarUtil;
import com.xiyu.R;
import com.xiyu.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册成功界面
 */
public class RegisterSuccessActivity extends BaseActivity {

    @BindView(R.id.nav_bar)
    NavigationBar navigationBar;
    @BindView(R.id.tv_content)
    TextView contentTv;

    @OnClick({R.id.tv_content})
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.tv_content:
                startActivity(MainActivity.class);
                break;
            default:
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        setStatusBarDark();
        navigationBar.setTitleSize(18.5f);
        navigationBar.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String html = "恭喜您成功注册“喜鱼优品”，请等待客服审核并激活账号。<b><font color=\"#1976FB\">浏览商品</font></b>";
        contentTv.setText(Html.fromHtml(html));
    }
}
