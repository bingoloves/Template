package com.learn.template.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ThumbnailImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.learn.core.adapter.listview.CommonAdapter;
import com.learn.core.adapter.listview.ViewHolder;
import com.learn.photo.PictureActivity;
import com.learn.photo.PictureData;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

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

    @BindView(R.id.gridView)
    GridView gridView;
    CommonAdapter adapter;
    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        String[] images = getResources().getStringArray(R.array.test_url);
        adapter = new CommonAdapter<String>(getContext(),R.layout.layout_grid_list_item,Arrays.asList(images)) {
            @Override
            protected void convert(ViewHolder viewHolder, String path, int position) {
                ImageView imageView = viewHolder.getView(R.id.iv_pic);
                ImageView deleteView = viewHolder.getView(R.id.iv_delete);
                Glide.with(getContext())
                        .asDrawable()
                        .load(path)
                        .apply(RequestOptions.placeholderOf(com.learn.photo.R.drawable.ic_loading))
                        .thumbnail(.2f)
                        .transition(withCrossFade())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(final Drawable resource, Transition<? super Drawable> transition) {
                                transition.transition(resource, new ThumbnailImageViewTarget(imageView) {
                                    @Override
                                    protected Drawable getDrawable(Object re) {
                                        return resource;
                                    }
                                });
                            }
                        });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<PictureData> list = new ArrayList<>();
                        for (int i = 0; i < images.length; i++) {
                            String pictureUrl = images[i];
                            ViewHolder holder = (ViewHolder) adapter.getViewHolders().get(i);
                            ImageView view = (ImageView) holder.getView(R.id.iv_pic);
                            PictureData e = new PictureData();
                            e.location = new int[2];
                            view.getLocationOnScreen(e.location);
                            e.matrixValue = new float[9];
                            view.getImageMatrix().getValues(e.matrixValue);
                            e.size = new int[]{view.getWidth(), view.getHeight()};
                            e.url = pictureUrl;
                            e.originalUrl = pictureUrl;
                            e.imageSize = new int[]{480, 360};
                            list.add(e);
                        }
                        PictureActivity.start(getContext(), list, position);
                    }
                });
            }
        };
        gridView.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }
}
