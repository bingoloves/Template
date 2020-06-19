package com.learn.template.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.learn.core.statusbar.StatusBarUtil;
import com.learn.core.utils.FragmentUtils;
import com.learn.template.R;
import com.learn.template.base.BaseActivity;
import com.learn.template.fragment.HomeFragment;
import com.learn.template.fragment.MeFragment;
import com.learn.template.fragment.ShopFragment;
import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.navigation_bottom)
    BottomNavigationView bottomNavigationView;
    private Fragment[] mFragments = new Fragment[3];
    private int curIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            curIndex = savedInstanceState.getInt("curIndex");
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragments[0] = HomeFragment.newInstance();
        mFragments[1] = ShopFragment.newInstance();
        mFragments[2] = MeFragment.newInstance();
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
                case R.id.navigation_shop:
                    showCurrentFragment(1);
                    return true;
                case R.id.navigation_me:
                    showCurrentFragment(2);
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
