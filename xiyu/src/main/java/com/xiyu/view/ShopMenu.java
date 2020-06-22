package com.xiyu.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xiyu.R;

/**
 * Created by bingo on 2020/6/21.
 * 购物车图标 带角标
 */

public class ShopMenu extends FrameLayout{
    private Context context;
    private TextView badgeTv;
    public ShopMenu(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public ShopMenu(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ShopMenu(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        inflate(context, R.layout.layout_shop_menu, this);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        badgeTv = findViewById(R.id.tv_badge);
        badgeTv.setVisibility(GONE);
    }

    public void setBadgeNum(int num){
        badgeTv.setVisibility(VISIBLE);
        if (num>99){
            badgeTv.setText("99+");
        } else {
            badgeTv.setText(num+"");
        }
    }
}
