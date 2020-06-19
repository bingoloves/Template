package com.learn.pay;

public class TestRequestUtils {
    /**
     * 模拟服务器返回微信支付所需信息
     */
    public static WxPayBean getWxPayConfig() {
        WxPayBean weChatPayModel = new WxPayBean();
        weChatPayModel.setAppId("wx8888888888888888");
        weChatPayModel.setPackageStr("com.learn.template");//"Sign=WXPay"
        weChatPayModel.setNonceStr("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        weChatPayModel.setPartnerId("1900000109");
        weChatPayModel.setPrepayId("WX1217752501201407033233368018");
        weChatPayModel.setSign("C380BEC2BFD727A4B6845133519F3AD6");
        weChatPayModel.setTimestamp(1412000000+"");
        return weChatPayModel;
    }

    /**
     * 模拟服务器返回支付宝订单信息
     */
    public static String getAlipayOrderInfo() {
        return "testOrderInfo";
    }
}
