package com.learn.photo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ThumbnailImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.learn.photo.nine.NineGridImageView;
import com.learn.photo.nine.NineGridImageViewAdapter;
import com.learn.photo.photo.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by bingo on 2020/7/7 0007.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    private Context context;
    private List<String> data;
    public PictureAdapter(Context context,List<String> list){
        this.context = context;
        this.data = list;
    }
    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final PictureViewHolder holder = PictureViewHolder.newInstance(parent, viewType);
        holder.iv_picture.setGap(Utils.dip2px(parent.getContext(), 4));
        return holder;
    }

    @Override
    public void onBindViewHolder(final PictureViewHolder holder, int position) {
        holder.tv_name.setText("图片标题");
        holder.tv_content.setText("内容");
        String images = data.get(position);
        String[] imagesArr = images.split(",");
        final List<String> pictureUrls = Arrays.asList(imagesArr);
        if (pictureUrls != null && pictureUrls.size() > 0) {
            if (pictureUrls.size() == 1) {
                holder.iv_picture.setSingleImgSize(480, 360);
            }
            holder.iv_picture.setVisibility(View.VISIBLE);
            holder.iv_picture.setAdapter(new NineGridImageViewAdapter() {
                @Override
                protected void onDisplayImage(Context context, final ImageView imageView, int position) {
                    String url = pictureUrls.get(position);
                    Glide.with(context)
                            .asDrawable()
                            .load(url)
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
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
                }

                @Override
                protected void onItemImageClick(Context context, ImageView imageView, int position) {
                    super.onItemImageClick(context, imageView, position);
                    ArrayList<PictureData> list = new ArrayList<>();
                    for (int i = 0; i < pictureUrls.size(); i++) {
                        String pictureUrl = pictureUrls.get(i);
                        ImageView view = (ImageView) holder.iv_picture.getChildAt(i);
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
                    PictureActivity.start(context, list, position);
                }
            });
            holder.iv_picture.setImagesCount(pictureUrls.size());
        } else {
            holder.iv_picture.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_content;
        NineGridImageView iv_picture;
        PictureViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_picture = itemView.findViewById(R.id.iv_pictrure);
        }

         public static PictureViewHolder newInstance(ViewGroup parent,int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_nine_grid_view, parent, false);
            return new PictureViewHolder(view);
        }
    }
}