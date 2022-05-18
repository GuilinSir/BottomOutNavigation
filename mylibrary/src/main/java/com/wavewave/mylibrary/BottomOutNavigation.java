package com.wavewave.mylibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author wavewave
 * @CreateDate: 2020/10/28 10:23 AM
 * @Description: 底部导航 选中突出View 背景
 * @Version: 1.0
 */
public class BottomOutNavigation extends View {
    private Paint mPaint;
    //起始点
    private int beginY = dip2px(60);
    //边距
    private int margin = dip2px(40);
    /**
     * 默认 突出最高点 Y
     */
    private int minHeight = dip2px(10);

    //第几个从0开始
    private int count = 1;
    /**
     * 默认3个 根据实际情况写
     */
    private int maxCount = 3;
    public static String TAG = "LineView";
    private int height;
    private int width;
    private Path mPath;

    public BottomOutNavigation(Context context) {
        this(context, null);
    }

    public BottomOutNavigation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomOutNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.WHITE);
        //抗锯齿
        mPaint.setAntiAlias(true);



    }

    /**
     * 设置选择
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        height = dm.heightPixels;
        width = dm.widthPixels;
        //单个所占大小
        int i = (width - 2 * margin) / maxCount;
        Log.e(TAG, "he:" + height);
        mPath.reset();


        //长方形
        mPath.moveTo(0, 0);
        mPath.addRect(margin, beginY, width - margin , 0, Path.Direction.CW);

        //左边的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(margin, beginY/2, beginY/2, Path.Direction.CW);

        //右边的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(width-margin, beginY/2, beginY/2, Path.Direction.CW);

        //点击时候的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(i / 2 + i * (count - 1) + margin, minHeight, beginY / 2, Path.Direction.CW);

        //左边的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(margin, beginY/2, beginY/2, Path.Direction.CW);

        //右边的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(width-margin, beginY/2, beginY/2, Path.Direction.CW);

        //长方形
        mPath.moveTo(0, 0);
        mPath.addRect(margin, beginY, width - margin ,0, Path.Direction.CW);

        //左边的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(margin, beginY/2, beginY/2, Path.Direction.CW);

        //右边的圆
        mPath.moveTo(0, 0);
        mPath.addCircle(width-margin, beginY/2, beginY/2, Path.Direction.CW);

        mPath.close(); //封闭path路径
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 根据屏幕的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
