package com.learn.core.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.learn.core.R;

import java.lang.reflect.Field;

/**
 * Created by bingo on 2020/6/21.
 */

public class BottomNavigationViewHelper {
    /**
     * 使用时 item 个数大于 3 个时会有位移动画，设置底部图标和字体都显示并去掉点击动画  android 28以下
     * 不推荐使用，现在一般设置如下
     * setItemTextAppearanceActive()/setItemTextAppearanceInactive() 方法 + app:labelVisibilityMode="labeled" 取消位移动画
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

    /**
     * 设置图标大小
     * @param context
     * @param navigation
     * @param iconSize
     */
    public static void adjustNavigationIcoSize(Context context,BottomNavigationView navigation,float iconSize){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, iconSize, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, iconSize, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }
}
