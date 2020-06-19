package com.learn.template.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.learn.core.glide.GlideHelper;
import com.learn.core.utils.LogUtils;
import com.learn.multistate.MultistateLayout;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

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
    @BindView(R.id.multi_layout)
    MultistateLayout multistateLayout;
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        multistateLayout.setStatus(MultistateLayout.LOADING);
        multistateLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                multistateLayout.setStatus(MultistateLayout.SUCCESS);
            }
        },1500);

        String[] images = getResources().getStringArray(R.array.test_url);
        //banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //banner.setBannerTitles();
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //Glide.with(context).load(path).into(imageView);
                GlideHelper.loadRound(context,(String)path,imageView,20);
            }
        });
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(Arrays.asList(images))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        LogUtils.e("position = "+position);
                    }
                }).start();
    }

    @Override
    protected void lazyLoad() {
        LogUtils.e("home lazyLoad");
    }

    @Override
    protected void refreshLoad() {
        LogUtils.e("home refreshLoad");
    }
}
