package com.xiyu.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import com.learn.core.statusbar.StatusBarUtil;
import com.learn.core.utils.FragmentUtils;
import com.xiyu.R;
import com.xiyu.base.BaseActivity;
import com.xiyu.fragment.ClassifyFragment;
import com.xiyu.fragment.HomeFragment;
import com.xiyu.fragment.MeFragment;
import com.xiyu.fragment.ShopFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation_bottom)
    BottomNavigationView bottomNavigationView;
    private Fragment[] mFragments = new Fragment[4];
    private int curIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mFragments[0] = HomeFragment.newInstance();
        mFragments[1] = ClassifyFragment.newInstance();
        mFragments[2] = ShopFragment.newInstance();
        mFragments[3] = MeFragment.newInstance();
        FragmentUtils.add(getSupportFragmentManager(), mFragments, R.id.fragment_container, curIndex);
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.colorPrimaryDark));
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
}
