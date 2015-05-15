package me.decimo.widget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Random;

/**
 * Created by Sak-Ka-RIN on 15/5/2558.
 */
public class DynamicWheelView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] value_degree;
    private RectF mRectf;
    private float temp = 0;

    public DynamicWheelView(Context context) {
        super(context);

        init();
    }

    public DynamicWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        value_degree = new float[] {
                72,
                72,
                72,
                72,
                72
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        mRectf = new RectF(0, 0, parentWidth, parentWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Random r;

        for (int i = 0; i < value_degree.length; i++) {
            if ( i == 0 ) {
                r = new Random();
                int color = Color.argb(100, r.nextInt(256), r.nextInt(256), r.nextInt(256));
                mPaint.setColor(color);
                canvas.drawArc(mRectf, 0, value_degree[i], true, mPaint);
            } else {
                temp += value_degree[i - 1];
                r = new Random();
                int color = Color.argb(255, r.nextInt(256), r.nextInt(256), r.nextInt(256));
                mPaint.setColor(color);
                canvas.drawArc(mRectf, temp, value_degree[i], true, mPaint);
            }
        }
    }
}
