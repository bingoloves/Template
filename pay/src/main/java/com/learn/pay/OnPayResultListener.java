package com.learn.pay;

public interface OnPayResultListener {
    void onPaySuccess();

    void onPayError(int errorCode, String errorStr);

    void onPayCancel();
}
