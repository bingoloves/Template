package com.learn.core.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.learn.core.R;

/**
 * glide 图片加载帮助类
 */
public class GlideHelper {
    private static final int PLACE_HOLDER = R.drawable.placeholder;
    public static void loadCircle(Context context, String url, ImageView imageView){
        loadCircle(context,url,imageView, PLACE_HOLDER);
    }
    public static void loadCircle(Context context, String url, ImageView imageView,int placeholder){
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(placeholder)//图片加载出来前，显示的图片
                .fallback(placeholder) //url为空的时候,显示的图片
                .error(placeholder);//图片加载失败后，显示的图片
        Glide.with(context)
                .load(TextUtils.isEmpty(url)?placeholder:url)
                .apply(options)
                .into(imageView);
    }
    public static void loadRound(Context context, String url, ImageView imageView){
        loadRound(context,url,imageView,8);
    }
    public static void loadRound(Context context, String url, ImageView imageView,int round){
        loadRound(context,url,imageView,round,PLACE_HOLDER);
    }
    public static void loadRound(Context context, String url, ImageView imageView,int round,int placeholder){
        RequestOptions options = new RequestOptions()
                .transform(new GlideRoundTransform(context,round))
                .placeholder(placeholder)
                .fallback(placeholder)
                .error(placeholder);
        Glide.with(context)
                .load(TextUtils.isEmpty(url)?placeholder:url)
                .apply(options)
                .into(imageView);
    }
    /**
     * 对线上资源为空判断
     * @param url
     * @return
     */
    private String getLoadPath(String url){
        return TextUtils.isEmpty(url)?"":url;
    }
}
