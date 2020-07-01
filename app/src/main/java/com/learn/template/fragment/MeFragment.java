package com.learn.template.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.learn.component.archeaderview.LGradientArcHeaderView;
import com.learn.component.utils.UnreadMsgUtils;
import com.learn.component.widget.MsgView;
import com.learn.core.utils.LogUtils;
import com.learn.pay.OnPayResultListener;
import com.learn.pay.PayApi;
import com.learn.pay.TestRequestUtils;
import com.learn.pay.WxPayBean;
import com.learn.picker.adapter.ArrayWheelAdapter;
import com.learn.picker.adapter.NumericWheelAdapter;
import com.learn.picker.dataset.OptionDataSet;
import com.learn.picker.picker.BasePicker;
import com.learn.picker.picker.OptionPicker;
import com.learn.picker.picker.TimePicker;
import com.learn.picker.util.DateUtil;
import com.learn.picker.util.Util;
import com.learn.picker.widget.BasePickerView;
import com.learn.picker.widget.DefaultCenterDecoration;
import com.learn.picker.widget.PickerView;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;
import com.learn.template.bean.City;
import com.learn.template.bean.County;
import com.learn.template.bean.Province;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bingo on 2020/6/19 0019.
 */

public class MeFragment extends BaseFragment implements TimePicker.OnTimeSelectListener, OptionPicker.OnOptionSelectListener {

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.lg_header_view)
    LGradientArcHeaderView lGradientArcHeaderView;
    @BindView(R.id.msgView)
    MsgView msgView;

    @OnClick({R.id.wxpay,R.id.alipay,R.id.time,R.id.address})
    public void clickEvent(View view){
        switch (view.getId()){
            case R.id.wxpay:
                WxPayBean wxPayConfig = TestRequestUtils.getWxPayConfig();
                PayApi.wxPay(wxPayConfig, new OnPayResultListener() {
                    @Override
                    public void onPaySuccess() {
                        LogUtils.e("wx onPaySuccess");
                    }

                    @Override
                    public void onPayError(int errorCode, String errorStr) {
                        LogUtils.e("wx onPayError:"+errorStr+",errorCode = "+errorCode);
                    }

                    @Override
                    public void onPayCancel() {
                        LogUtils.e("wx onPayCancel");
                    }
                });
                break;
            case R.id.alipay:
                String alipayOrderInfo = TestRequestUtils.getAlipayOrderInfo();
                PayApi.aliPay(getActivity(), alipayOrderInfo, new OnPayResultListener() {
                    @Override
                    public void onPaySuccess() {
                        LogUtils.e("ali onPaySuccess");
                    }

                    @Override
                    public void onPayError(int errorCode, String errorStr) {
                        LogUtils.e("ali onPayError:"+errorStr+",errorCode = "+errorCode);
                    }

                    @Override
                    public void onPayCancel() {
                        LogUtils.e("ali onPayCancel");
                    }
                });
                break;
            case R.id.time:
                showTime();
                break;
            case R.id.address:
                selectAddress();
                break;
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {
        //UnreadMsgUtils.show(msgView,102);
        if (msgView != null) {
            UnreadMsgUtils.show(msgView, 88);
        }
        PickerView mPickerView = view.findViewById(R.id.pickerview);
        //mPickerView.setAdapter(new NumericWheelAdapter(1, 10));
        mPickerView.setAdapter(new ArrayWheelAdapter<String>(Arrays.asList(new String[]{"男","女","人妖"})));
        // 覆盖xml中的水平方向
        mPickerView.setHorizontal(false);
        mPickerView.setTextSize(15, 22);
        //mPickerView.setIsCirculation(true);
        //mPickerView.setAlignment(Layout.Alignment.ALIGN_CENTER);
        mPickerView.setCanTap(false);
        mPickerView.setDisallowInterceptTouch(false);
        // 覆盖xml设置的7
        mPickerView.setVisibleItemCount(5);
        mPickerView.setItemSize(40.5f);
        // 格式化内容
        /*mPickerView.setFormatter(new BasePickerView.Formatter() {
            @Override
            public CharSequence format(BasePickerView pickerView, int position,
                                       CharSequence charSequence) {
                return charSequence + "万年";
            }
        });*/
        int margin = Util.dip2px(getContext(), 5);
        DefaultCenterDecoration centerDecoration = new DefaultCenterDecoration(getActivity()).setLineColor(Color.GREEN)
                        //.setMargin(margin, -margin, margin, -margin)
                        .setLineWidth(1)
                        .setDrawable(Color.RED);
        mPickerView.setCenterDecoration(centerDecoration);
    }
    public void showTime(){
        TimePicker mTimePicker = new TimePicker.Builder(getContext(),TimePicker.TYPE_YEAR|TimePicker.TYPE_MONTH|TimePicker.TYPE_DAY,this)
                        // 设置不包含超出的结束时间<=
                        //.setContainsEndDate(false)
                        // 设置时间间隔为30分钟
                        //.setTimeMinuteOffset(30).setRangDate(1517771651000L, 1577976666000L)
//                        .setFormatter(new TimePicker.DefaultFormatter() {
//                            @Override
//                            public CharSequence format(TimePicker picker, int type, int position, long value) {
//                                if (type == TimePicker.TYPE_MIXED_DATE) {
//                                    CharSequence text;
//                                    int dayOffset = DateUtil.getDayOffset(value, System.currentTimeMillis());
//                                    if (dayOffset == 0) {
//                                        text = "今天";
//                                    } else if (dayOffset == 1) {
//                                        text = "明天";
//                                    } else { // xx月xx日 星期 x
//                                        text = mDateFormat.format(value);
//                                    }
//                                    return text;
//                                }
//                                return super.format(picker, type, position, value);
//                            }
//                        })
                        .create();

        mTimePicker.show();
    }

    /**
     * 设置单项列表
     */
    private void showChooseItem(){

    }

    private void selectAddress(){
        // 设置CenterDecoration
        DefaultCenterDecoration decoration = new DefaultCenterDecoration(getContext());
        decoration.setLineColor(Color.RED)
                //.setDrawable(Color.parseColor("#999999"))
                .setLineWidth(1)
                .setMargin(Util.dip2px(getContext(), 10), Util.dip2px(getContext(), -3), Util.dip2px(getContext(), 10), Util.dip2px(getContext(), -3))
        ;
        OptionPicker mPicker = new OptionPicker.Builder(getContext(), 3, this).setInterceptor(new BasePicker.Interceptor() {
            @Override
            public void intercept(PickerView pickerView, LinearLayout.LayoutParams params) {
                int level = (int) pickerView.getTag();
                LogUtils.e("level = "+level);
                pickerView.setVisibleItemCount(3);
                // setInterceptor 可以根据level区分设置pickerview属性
                pickerView.setCenterDecoration(decoration);
                pickerView.setTextSize(15, 20);
            }
        }).create();
        List<Province> data = createForeignData();
        mPicker.setData(data);
        mPicker.show();
    }
    private List<Province> createForeignData() {
        List<Province> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Province province = new Province();
            province.id = 100 * i;
            province.name = "省" + i;
            province.citys = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                City city = new City();
                city.id = 10 * j;
                city.name = "市" + j;
                city.counties = new ArrayList<>();
                for (int k = 0; k < 10; k++) {
                    County county = new County();
                    county.id = k;
                    county.name = "县" + k;
                    city.counties.add(county);
                }
                province.citys.add(city);
            }
            list.add(province);
        }
        return list;
    }
    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }

    @Override
    public void onTimeSelect(TimePicker picker, Date date) {

    }

    @Override
    public void onOptionSelect(OptionPicker picker, int[] selectedPosition, OptionDataSet[] selectedOptions) {

    }
}
