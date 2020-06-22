package com.xiyu;

import android.app.Application;

import com.learn.core.crash.CaocConfig;
import com.learn.core.utils.AppConfig;
import com.learn.core.utils.LogUtils;
import com.learn.core.utils.Utils;
import com.learn.multistate.MultistateLayout;
import com.learn.pay.PayApi;
import com.xiyu.activity.MainActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2020/6/19 0019.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLog();
        initCrash();
        AppConfig.getInstance().init(this);//头条适配方案
        Utils.init(this);
        PayApi.init(this);
        initMultisateLayout();
    }
    public void initLog() {
        LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-yyyy-MM-dd$fileExtension"
                .setFileExtension(".log")// 设置日志文件后缀
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setSingleTagSwitch(true)// 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setConsoleFilter(LogUtils.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1)// log 栈深度，默认为 1
                .setStackOffset(0)// 设置栈偏移，比如二次封装的话就需要设置，默认为 0
                .setSaveDays(3)// 设置日志可保留天数，默认为 -1 表示无限时长
                // 新增 ArrayList 格式化器，默认已支持 Array, Throwable, Bundle, Intent 的格式化输出
                .addFormatter(new LogUtils.IFormatter<ArrayList>() {
                    @Override
                    public String format(ArrayList arrayList) {
                        return "LogUtils Formatter ArrayList { " + arrayList.toString() + " }";
                    }
                })
                .setFileWriter(null);
        LogUtils.i(config.toString());
    }
    /**
     * 异常捕捉
     */
    private void initCrash() {
        CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(BuildConfig.DEBUG) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
//                .errorDrawable(R.mipmap.ic_launcher) //错误图标
                .restartActivity(MainActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }
    /**
     * 初始化多状态布局
     */
    private void initMultisateLayout() {
        MultistateLayout.getBuilder()
                .setLoadingText("加载中...")
                .setLoadingTextSize(14)
                .setLoadingTextColor(R.color.colorPrimary)
                //.setLoadingViewLayoutId(R.layout.custom_loading_view) //如果设置了自定义loading视图,上面三个方法失效
                .setEmptyImgId(R.drawable.ic_empty)
                .setErrorImgId(R.drawable.ic_error_zhihu)
                .setNoNetWorkImgId(R.drawable.ic_no_network_zhihu)
                .setEmptyImageVisible(true)
                .setErrorImageVisible(true)
                .setNoNetWorkImageVisible(true)
                //.setEmptyText(getString(R.string.custom_empty_text))
                //.setErrorText(getString(R.string.custom_error_text))
                //.setNoNetWorkText(getString(R.string.custom_nonetwork_text))
                .setAllTipTextSize(12)
                .setAllTipTextColor(R.color.text_color_child)
                .setAllPageBackgroundColor(R.color.white)
                //.setReloadBtnText(getString(R.string.custom_reloadBtn_text))
                .setReloadBtnTextSize(12)
                .setReloadBtnTextColor(R.color.colorPrimary)
                .setReloadBtnBackgroundResource(R.drawable.selector_btn)
                .setReloadBtnVisible(false)
                .setReloadClickArea(MultistateLayout.FULL);
    }
}
