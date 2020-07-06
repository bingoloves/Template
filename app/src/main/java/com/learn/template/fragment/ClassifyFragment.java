package com.learn.template.fragment;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.learn.core.adapter.recyclerview.CommonAdapter;
import com.learn.core.adapter.recyclerview.base.ViewHolder;
import com.learn.core.adapter.recyclerview.utils.DividerItemDecoration;
import com.learn.core.glide.GlideHelper;
import com.learn.core.utils.LogUtils;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by bingo on 2020/6/19 0019.
 * 分类
 */

public class ClassifyFragment extends BaseFragment {

    public static ClassifyFragment newInstance() {
        Bundle args = new Bundle();
        ClassifyFragment fragment = new ClassifyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.rv_left)
    RecyclerView leftRv;
    @BindView(R.id.rv_right)
    RecyclerView rightRv;

    @Override
    protected int getContentView() {
        return R.layout.fragment_classify;
    }
    private CommonAdapter leftAdapter;
    private CommonAdapter rightAdapter;
    //private List<String> leftList = new ArrayList<>();
    //private List<String> leftList = new ArrayList<>();
    private String[] types = {"男装","女装","男鞋","女鞋","电脑","手机","家具","办公用品","医药","保健","乐器","图书","手表","内衣","母婴用品","奢侈品"};
    private String[] contents = {"男装","女装","男鞋","女鞋","电脑","手机","家具","办公用品","医药","保健","乐器","图书","手表","内衣","母婴用品","奢侈品"};

    private int leftClickPosition = 0;
    private boolean mIsFromClick = false;
    @Override
    protected void initView(View view) {
        LiveEventBus
                .get("send_msg", String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        toast(s);
                    }
                });
        //左侧类型
        LinearLayoutManager leftManager = new LinearLayoutManager(getContext());
        LinearLayoutManager rightManager = new LinearLayoutManager(getContext());
        leftRv.setLayoutManager(leftManager);
        leftAdapter = new CommonAdapter<String>(getContext(),R.layout.layout_classify_left_item, Arrays.asList(types)) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                holder.setText(R.id.tv_type_name,o);
                holder.setTextColor(R.id.tv_type_name,leftClickPosition == position ? Color.BLUE : Color.BLACK);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        leftClickPosition = position;
                        leftAdapter.notifyDataSetChanged();
                        mIsFromClick = true;//不走onScrolled，防止来回调
                        //此方法可以置顶
                        rightManager.scrollToPositionWithOffset(position, 0);
                        mIsFromClick = false;//放开
                    }
                });
            }
        };
        leftRv.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL,6, R.color.divider));
        leftRv.setAdapter(leftAdapter);

        //右侧内容
        rightRv.setLayoutManager(rightManager);
        rightAdapter = new CommonAdapter<String>(getContext(),R.layout.layout_classify_right_item, Arrays.asList(types)) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                holder.setText(R.id.tv_content_name,o);
                RecyclerView contentRv = holder.getView(R.id.rv_content);
                String[] images = getResources().getStringArray(R.array.test_url);
                contentRv.setLayoutManager(new GridLayoutManager(getContext(),3));
                contentRv.setAdapter(new CommonAdapter<String>(getContext(),R.layout.layout_classify_right_content_item,Arrays.asList(images)) {
                    @Override
                    protected void convert(ViewHolder holder, String o, int position) {
                        ImageView imageView =  holder.getView(R.id.iv_goods);
                        GlideHelper.loadCircle(getContext(),o,imageView);
                    }
                });
            }
        };
        rightRv.setAdapter(rightAdapter);

        rightRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mIsFromClick) {//防止来回调
                    return;
                }
                changePosition();
            }
            private void changePosition() {
                int firstPosition = rightManager.findFirstVisibleItemPosition();
                if (leftClickPosition != firstPosition) {
                    leftClickPosition = firstPosition;
                    leftAdapter.notifyDataSetChanged();
                    //此方法无置顶效果
                    leftRv.scrollToPosition(leftClickPosition);
                }
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
