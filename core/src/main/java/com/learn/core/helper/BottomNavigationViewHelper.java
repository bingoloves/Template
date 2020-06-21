package com.learn.core.helper;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

/**
 * Created by bingo on 2020/6/21.
 */

public class BottomNavigationViewHelper {
    /**
     * 使用时 item 个数大于 3 个时会有位移动画，设置底部图标和字体都显示并去掉点击动画  android 28以下
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //no inspection RestrictedApi
                //item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //no inspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
