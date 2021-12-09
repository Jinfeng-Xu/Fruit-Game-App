package component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by du on 17/7/18.
 */
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int mViewWidth;
    private TextPaint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();//获取当前用来绘制TextView的Paint对象
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{Color.GREEN, Color.BLUE, Color.YELLOW, Color.GREEN},
                        null,
                        Shader.TileMode.CLAMP);//根据view的宽设置一个LinearGradient渐变渲染器
                mPaint.setShader(mLinearGradient);//给Paint对象设置渲染器
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);//矩阵平移
            mLinearGradient.setLocalMatrix(mGradientMatrix);//通过矩阵的方式不断平移渐变效果，产生闪动效果
            postInvalidateDelayed(200);
        }
    }
}
