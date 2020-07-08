package com.learn.template.fragment;

import android.os.Bundle;
import android.view.View;

import com.learn.template.R;
import com.learn.template.base.BaseFragment;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by bingo on 2020/6/19 0019.
 */

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        String[] images = getResources().getStringArray(R.array.test_url);

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }
}
