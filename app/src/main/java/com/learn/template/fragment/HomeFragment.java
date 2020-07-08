package com.learn.template.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ThumbnailImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.learn.core.adapter.listview.CommonAdapter;
import com.learn.core.adapter.listview.ViewHolder;
import com.learn.core.utils.LogUtils;
import com.learn.photo.PictureActivity;
import com.learn.photo.PictureData;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;
import com.learn.template.view.NineGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    NineGridView gridView;

    CommonAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        String[] images = getResources().getStringArray(R.array.test_url);
        //gridView.setLayoutManager(new GridLayoutManager(getContext(),4));
        adapter = new CommonAdapter<String>(getContext(),R.layout.layout_grid_list_item,Arrays.asList(images)) {
            @Override
            protected void convert(ViewHolder viewHolder, String path, int position) {
                ImageView imageView = viewHolder.getView(R.id.iv_pic);
                ImageView deleteView = viewHolder.getView(R.id.iv_delete);
//                Glide.with(getContext()).load(path).into(imageView);
                Glide.with(getContext())
                        .asDrawable()
                        .load(path)
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
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<PictureData> list = new ArrayList<>();
                        List<String> datas = adapter.getDatas();
                        for (int i = 0; i < datas.size(); i++) {
                            String pictureUrl = datas.get(i);
                            ViewHolder holder = (ViewHolder) adapter.getViewHolders().get(i);
                            ImageView view = (ImageView) holder.getView(R.id.iv_pic);
                            int width = view.getWidth();
                            int height = view.getHeight();
                            LogUtils.e(width+"----"+height);
                            PictureData e = new PictureData();
                            e.location = new int[2];
                            view.getLocationOnScreen(e.location);
                            e.matrixValue = new float[9];
                            view.getImageMatrix().getValues(e.matrixValue);
                            e.size = new int[]{width,height};
                            e.url = pictureUrl;
                            e.originalUrl = pictureUrl;
                            e.imageSize = new int[]{width,height};//new int[]{480, 360};
                            list.add(e);
                        }
                        PictureActivity.start(getContext(), list, position);
                    }
                });
            }
        };
        gridView.setAdapter(adapter);
//        NineAdapter nineAdapter = new NineAdapter(getContext(),Arrays.asList(images));
//        gridView.setAdapter(nineAdapter);
        String[] images2 = getResources().getStringArray(R.array.test_url_2);
        gridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.update(Arrays.asList(images2));
            }
        },2000);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ArrayList<PictureData> list = new ArrayList<>();
//                        for (int i = 0; i < images.length; i++) {
//                            String pictureUrl = images[i];
//                            ViewHolder holder = (ViewHolder) adapter.getViewHolders().get(i);
//                            ImageView view = (ImageView) holder.getView(R.id.iv_pic);
//                            int width = view.getWidth();
//                            int height = view.getHeight();
//                            LogUtils.e(width+"----"+height);
//                            PictureData e = new PictureData();
//                            e.location = new int[2];
//                            view.getLocationOnScreen(e.location);
//                            e.matrixValue = new float[9];
//                            view.getImageMatrix().getValues(e.matrixValue);
//                            e.size = new int[]{width,height};
//                            e.url = pictureUrl;
//                            e.originalUrl = pictureUrl;
//                            e.imageSize = new int[]{width,height};//new int[]{480, 360};
//                            list.add(e);
//                        }
//                        PictureActivity.start(getContext(), list, position);
//            }
//        });
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }

//    public class NineAdapter extends BaseAdapter{
//        private List<String> date;
//        private Context context;
//
//        public List<String> getDate() {
//            return date;
//        }
//
//        public void setDate(List<String> date) {
//            this.date = date;
//        }
//
//        public NineAdapter(Context context, List<String> date){
//            this.context = context;
//            this.date = date;
//        }
//        public void update(List<String> date){
//             this.date = date;
//             notifyDataSetChanged();
//         }
//        @Override
//        public int getCount() {
//            return date.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            if(convertView==null){
//                convertView = LayoutInflater.from(context).inflate(R.layout.layout_grid_list_item,null);
//                holder= new ViewHolder();
//                holder.imageView = (ImageView)convertView.findViewById(R.id.iv_pic);
//                holder.deleteView = (ImageView)convertView.findViewById(R.id.iv_delete);
//                convertView.setTag(holder);
//            }else{
//                holder=(ViewHolder) convertView.getTag();
//            }
//            Glide.with(getContext())
//                        .asDrawable()
//                        .load(date.get(position))
//                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
//                        .thumbnail(.2f)
//                        .transition(withCrossFade())
//                        .into(new SimpleTarget<Drawable>() {
//                            @Override
//                            public void onResourceReady(final Drawable resource, Transition<? super Drawable> transition) {
//                                transition.transition(resource, new ThumbnailImageViewTarget(holder.imageView) {
//                                    @Override
//                                    protected Drawable getDrawable(Object re) {
//                                        return resource;
//                                    }
//                                });
//                            }
//                        });
//            return convertView;
//        }
//        public class ViewHolder{
//            public ImageView imageView;
//            public ImageView deleteView;
//        }
//    }
}
