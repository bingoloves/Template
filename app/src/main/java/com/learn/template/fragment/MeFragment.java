package com.learn.template.fragment;

import android.os.Bundle;
import android.view.View;

import com.learn.component.archeaderview.LGradientArcHeaderView;
import com.learn.core.utils.LogUtils;
import com.learn.pay.OnPayResultListener;
import com.learn.pay.PayApi;
import com.learn.pay.TestRequestUtils;
import com.learn.pay.WxPayBean;
import com.learn.template.R;
import com.learn.template.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by bingo on 2020/6/19 0019.
 */

public class MeFragment extends BaseFragment {

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @BindView(R.id.lg_header_view)
    LGradientArcHeaderView lGradientArcHeaderView;

    @OnClick({R.id.wxpay,R.id.alipay})
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
        }
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void refreshLoad() {
    }
}
