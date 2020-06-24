package com.learn.template.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.learn.component.utils.CompatResourceUtils;
import com.learn.component.widget.DotView;
import com.learn.core.helper.BottomNavigationViewHelper;
import com.learn.core.statusbar.StatusBarUtil;
import com.learn.core.utils.FragmentUtils;
import com.learn.template.R;
import com.learn.template.base.BaseActivity;
import com.learn.template.fragment.ClassifyFragment;
import com.learn.template.fragment.HomeFragment;
import com.learn.template.fragment.MeFragment;
import com.learn.template.fragment.ShopFragment;

import java.util.Random;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation_bottom)
    BottomNavigationView bottomNavigationView;
    private Fragment[] mFragments = new Fragment[4];
    private int curIndex;
    private DotView[] dotViews = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.adjustNavigationIcoSize(this,bottomNavigationView,22);
        mFragments[0] = HomeFragment.newInstance();
        mFragments[1] = ClassifyFragment.newInstance();
        mFragments[2] = ShopFragment.newInstance();
        mFragments[3] = MeFragment.newInstance();
        FragmentUtils.add(getSupportFragmentManager(), mFragments, R.id.fragment_container, curIndex);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorPrimaryDark));
        initUnReadMessageViews();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    showCurrentFragment(0);
                    return true;
                case R.id.navigation_classify:
                    showCurrentFragment(1);
                    return true;
                case R.id.navigation_shop:
                    showCurrentFragment(2);
                    return true;
                case R.id.navigation_me:
                    showCurrentFragment(3);
                    return true;
            }
            return false;
        }
    };
    private void showCurrentFragment(int index) {
        FragmentUtils.showHide(curIndex = index, mFragments);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("curIndex", curIndex);
    }

    /**
     * 初始化红点 BottomNavigationView
     */
    private void initUnReadMessageViews() {
        BottomNavigationMenuView menuView = null;
        for (int i = 0; i < bottomNavigationView.getChildCount(); i++) {
            View child = bottomNavigationView.getChildAt(i);
            if (child instanceof BottomNavigationMenuView) {
                menuView = (BottomNavigationMenuView) child;
                break;
            }
        }
        if (menuView != null) {
            int dp8 = CompatResourceUtils.getDimensionPixelSize(this, R.dimen.dp_8);
            dotViews = new DotView[menuView.getChildCount()];
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView.LayoutParams params = new BottomNavigationItemView.LayoutParams(i == menuView.getChildCount() - 1 ? dp8 : dp8 * 2, 0);
                params.gravity = Gravity.CENTER_HORIZONTAL;
                params.leftMargin = dp8 * 3;
                params.topMargin = dp8 / 2;
                BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
                DotView dotView = new DotView(this);
                dotView.setBackgroundColor(Color.RED);
                dotView.setTextColor(Color.WHITE);
                dotView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                itemView.addView(dotView, params);
                if (i < menuView.getChildCount() - 1) {
                    dotView.setUnReadCount(new Random().nextInt(120) + 1);
                }
                dotViews[i] = dotView;
            }
        }
    }
}
