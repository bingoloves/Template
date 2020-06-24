package com.learn.component.archeaderview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * <br>Email:1006368252@qq.com
 * <br>QQ:1006368252
 * <br><a href="https://github.com/JustinRoom/JSCKit" target="_blank">https://github.com/JustinRoom/JSCKit</a>
 *
 * @author jiangshicheng
 */
public class ArcHeaderDrawable extends Drawable {

    public static final int DIRECTION_DOWN_OUT_SIDE = 0;
    public static final int DIRECTION_DOWN_IN_SIDE = 1;

    @IntDef({DIRECTION_DOWN_OUT_SIDE, DIRECTION_DOWN_IN_SIDE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
    }

    private Path mPath = new Path();
    private int mArcHeight;// 圆弧高度
    private int direction;
    private Bitmap mBitmap;
    private Paint mPaint;

    public ArcHeaderDrawable(@NonNull Context context, @DrawableRes int drawableId, int mArcHeight, @Direction int direction) {
        this(BitmapFactory.decodeResource(context.getResources(), drawableId), mArcHeight, direction);
    }

    public ArcHeaderDrawable(@NonNull Bitmap bitmap, int mArcHeight, @Direction int direction) {
        mBitmap = bitmap;
        this.mArcHeight = mArcHeight;
        this.direction = direction;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(bitmapShader);
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        mPath.reset();
        int w = getIntrinsicWidth();
        int h = getIntrinsicHeight();
        mPath.moveTo(0, 0);
        switch (direction) {
            case DIRECTION_DOWN_OUT_SIDE:
                mPath.lineTo(0, h - mArcHeight);
                mPath.quadTo(w / 2.0f, h + mArcHeight, w, h - mArcHeight);
                mPath.lineTo(w, 0);
                break;
            case DIRECTION_DOWN_IN_SIDE:
                mPath.lineTo(0, h);
                mPath.quadTo(w / 2.0f, h - mArcHeight * 2, w, h);
                mPath.lineTo(w, 0);
                break;
        }
        mPath.close();

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}  