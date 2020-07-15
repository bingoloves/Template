package com.learn.template.fragment;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.learn.component.archeaderview.LGradientArcHeaderView;
import com.learn.core.adapter.recyclerview.CommonAdapter;
import com.learn.core.adapter.recyclerview.base.ViewHolder;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;
import com.learn.template.view.UMExpandLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by bingo on 2020/6/19 0019.
 * 可折叠列表
 */

public class ExpandLayoutFragment extends BaseFragment{

    public static ExpandLayoutFragment newInstance() {
        Bundle args = new Bundle();
        ExpandLayoutFragment fragment = new ExpandLayoutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.lg_header_view)
    LGradientArcHeaderView lGradientArcHeaderView;
    @BindView(R.id.rv_content)
    RecyclerView contentTv;

    CommonAdapter<String> adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.fragment_expand_layout;
    }

    @Override
    protected void initView(View view) {
        list.add("测试标题1");
        list.add("测试标题2");
        list.add("测试标题3");
        list.add("测试标题4");
        contentTv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CommonAdapter<String>(getContext(),R.layout.layout_expend_layout_item,list) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tv_title,s);
                UMExpandLayout expandLayout = holder.getView(R.id.expand_layout);
                expandLayout.initExpand(false);
                View arrow = holder.getView(R.id.arrow);
                //initAnimator(arrow);
                RecyclerView childRv = holder.getView(R.id.rv_child);
                childRv.setLayoutManager(new LinearLayoutManager(getContext()));
                childRv.setAdapter(new CommonAdapter<String>(getContext(),R.layout.layout_classify_left_item,list) {
                    @Override
                    protected void convert(ViewHolder holder, String o, int position) {

                    }
                });
                holder.setOnClickListener(R.id.tv_title, new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        expandLayout.resetViewDimensions();
                        expandLayout.toggleExpand();
                        // 旋转的方式一 属性
                        //arrow.setRotation(expandLayout.isExpand()?180:0);
                        // 旋转的方式二 动画
                        if (expandLayout.isExpand()){
                            //mAnimator.start();//动画开始
                            startAnimation(arrow);
                        } else {
                            //mAnimator.pause();//动画结束
                            endAnimation(arrow);
                        }
                    }
                });
            }
        };
        contentTv.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
    }

    /**
     * 设置开始动画
     */
    private void startAnimation(View view){
        Animation rotateAnimation =  new RotateAnimation(0f,180f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        view.setAnimation(rotateAnimation);
    }
    private void endAnimation(View view){
        RotateAnimation rotateAnimation  = new RotateAnimation(180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //LinearInterpolator lin = new LinearInterpolator();
        //rotateAnimation.setInterpolator(lin);
        rotateAnimation.setDuration(2000);
        //rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        //rotate.setStartOffset(10);
        view.setAnimation(rotateAnimation);
    }
    private ObjectAnimator mAnimator;
    public static final int STATE_PLAYING = 1;//正在播放
    public static final int STATE_PAUSE = 2;//暂停
    public static final int STATE_STOP = 3;//停止
    public int mState;
    private void initAnimator(View view) {
        mState = STATE_STOP;
        mAnimator = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360.0f);
        mAnimator.setDuration(5000);//设定转一圈的时间
        mAnimator.setRepeatCount(Animation.INFINITE);//设定无限循环
        mAnimator.setRepeatMode(ObjectAnimator.RESTART);// 循环模式
        mAnimator.setInterpolator(new LinearInterpolator());// 匀速
    }
}
