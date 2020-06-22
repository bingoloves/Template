package com.xiyu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.learn.core.glide.GlideHelper;
import com.learn.core.utils.LogUtils;
import com.learn.multistate.MultistateLayout;
import com.xiyu.R;
import com.xiyu.activity.WebActivity;
import com.xiyu.base.BaseFragment;
import com.xiyu.dialog.DialogHelper;
import com.xiyu.view.ShopMenu;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
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
    @BindView(R.id.shopMenu)
    ShopMenu shopMenu;

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
        //banner.setBannerAnimation(Transformer.ZoomOutSlide);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(Arrays.asList(images))
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        LogUtils.e("position = "+position);
                        //WebActivity.load(getActivity(),"https://www.jianshu.com/p/fa7adfa90c68");
                        //DialogHelper.alert(getActivity());
                    }
                }).start();
        shopMenu.setBadgeNum(3);
        shopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DialogHelper.bottom(getActivity());
                DialogHelper.loading(getActivity(),"加载中...");
            }
        });
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }
}
