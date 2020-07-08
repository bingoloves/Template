package com.learn.template.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.learn.core.glide.GlideHelper;
import com.learn.core.utils.LogUtils;
import com.learn.core.utils.StringUtils;
import com.learn.multistate.MultistateLayout;
import com.learn.photo.PictureAdapter;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by bingo on 2020/6/19 0019.
 * 购物车页面
 */

public class ShopFragment extends BaseFragment {

    public static ShopFragment newInstance() {
        Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.rv_content)
    RecyclerView contentRv;

    @Override
    protected int getContentView() {
        return R.layout.fragment_shop;
    }
    private List<String> data = new ArrayList<>();
    @Override
    protected void initView(View view) {
        data.add("http://bpic.588ku.com/element_origin_min_pic/00/00/05/115732f1ac12d1d.jpg");
        data.add("http://bpic.588ku.com/element_origin_min_pic/00/00/05/115732f1ac12d1d.jpg,http://bpic.588ku.com/element_origin_min_pic/00/00/05/115732f1ac12d1d.jpg");
        data.add("http://bpic.588ku.com/element_origin_min_pic/00/00/05/115732f1ac12d1d.jpg,http://bpic.588ku.com/element_origin_min_pic/00/00/05/115732f1ac12d1d.jpg,http://bpic.588ku.com/element_origin_min_pic/00/00/05/115732f21927f8d.jpg");
        String[] images = getResources().getStringArray(R.array.test_url);
        data.add(StringUtils.join(",",images));
        contentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        PictureAdapter adapter = new PictureAdapter(getContext(),data);
        contentRv.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }
}
