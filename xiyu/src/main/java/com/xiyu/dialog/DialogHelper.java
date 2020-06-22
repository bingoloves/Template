package com.xiyu.dialog;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.learn.dialog.IDialog;
import com.learn.dialog.SuperDialog;
import com.xiyu.R;

import static com.learn.dialog.manager.ScreenUtil.getScreenWidth;

/**
 * Created by bingo on 2020/6/22 0022.
 * dialog 帮助类
 */

public class DialogHelper {
    /**
     * 通用的 alert
     * @param context
     * @param title
     * @param msg
     * @param onClickListener
     */
    public static void alert(Context context, String title, String msg, IDialog.OnClickListener onClickListener){
        new SuperDialog.Builder(context)
                .setTitle(title)
                .setAnimStyle(R.style.dialog_modal_center)
                .setContent(msg)
                .setPositiveButton(onClickListener)
                .show();
    }
    /**
     * 中间自定义弹窗
     * @param activity
     * @return
     */
    public static SuperDialog loading(Activity activity,String msg){
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_loading,null);
        TextView msgTv = view.findViewById(R.id.tv_loading);
        if (TextUtils.isEmpty(msg)){
            msgTv.setVisibility(View.GONE);
        } else {
            msgTv.setVisibility(View.VISIBLE);
            msgTv.setText(msg);
        }
        return new SuperDialog.Builder(activity)
                .setDialogView(view)
                .setCancelableOutSide(true)
                .setAnimStyle(R.style.dialog_modal_center)
                .show();
    }
    /**
     * 中间自定义弹窗
     * @param activity
     * @param layoutId
     * @return
     */
    public static SuperDialog centerAlert(Activity activity,int layoutId){
        return new SuperDialog.Builder(activity)
                .setDialogView(layoutId)
                .setGravity(Gravity.CENTER)
                .setWidth((int) (getScreenWidth(activity) * 0.85f))
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setAnimStyle(R.style.dialog_modal_center)
                .show();
    }

    /**
     * 底部自定义弹窗
     * @param activity
     * @return
     */
    public static SuperDialog bottom(Activity activity){
       return bottom(activity,R.layout.layout_bottom_dialog);
    }
    /**
     * 底部自定义弹窗
     * @param activity
     * @param layoutId
     * @return
     */
    public static SuperDialog bottom(Activity activity,int layoutId){
        return new SuperDialog.Builder(activity)
                .setDialogView(layoutId)
                .setFullScreenWidth()
                .setHeight(WindowManager.LayoutParams.WRAP_CONTENT)
                .setGravity(Gravity.BOTTOM)
                .setAnimStyle(R.style.dialog_modal_bottom)
                .show();
    }
}
