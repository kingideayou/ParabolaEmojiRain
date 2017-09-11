package me.next.library;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class ParabolaImageView extends ImageView {
    private Context mContext;
    private int mDefaultSize;
    private int mStartX;
    private int mStartY;
    private int mEndX;
    private int mEndY;
    private int mControlX;
    private int mControlY;

    private float mAnimValue;
    private ValueAnimator mValueAnimator;
    private OnAnimEndListener mOnAnimEndListener;

    public ParabolaImageView(Context context) {
        this(context, null);
    }

    public ParabolaImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParabolaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void init() {

        mDefaultSize = DisplayUtils.dp2px(mContext, 60);
        mValueAnimator = ValueAnimator.ofFloat(0, 1f);
        mValueAnimator.setDuration(1600);
        mValueAnimator.setInterpolator(new AccelerateInterpolator(.6f));
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimValue = (float) animation.getAnimatedValue();
                setXY();
                invalidate();
            }
        });
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setLayerType(View.LAYER_TYPE_NONE, null);
                if (mOnAnimEndListener != null) {
                    mOnAnimEndListener.onAnimEnd();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = measureDimension(widthMeasureSpec);
        int h = measureDimension(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }

    public int measureDimension(int measureDir) {
        int size;
        int specMode = MeasureSpec.getMode(measureDir);
        int specSize = MeasureSpec.getSize(measureDir);

        switch (specMode) {
            case MeasureSpec.AT_MOST:
                size = Math.min(mDefaultSize, specSize);
                break;
            case MeasureSpec.EXACTLY:
                size = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                size = mDefaultSize;
                break;
            default:
                size = mDefaultSize;
                break;
        }
        return size;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = (int) getX();
        mStartY = (int) getY();
    }

    public void startAnim(final int targetX, final int targetY, int delayTime) {
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEndX = targetX;
                mEndY = targetY;
                mControlX = (mStartX + mEndX) / 2;
                mControlY = mStartY / 4;
                mValueAnimator.start();
            }
        }, delayTime);
    }

    public void setXY() {
        this.setX((1 - mAnimValue) * (1 - mAnimValue) * mStartX + 2 * mAnimValue * (1 - mAnimValue) * mControlX + mAnimValue * mAnimValue * mEndX);
        this.setY((1 - mAnimValue) * (1 - mAnimValue) * mStartY + 2 * mAnimValue * (1 - mAnimValue) * mControlY + mAnimValue * mAnimValue * mEndY);
    }

    public void resetView() {
        this.setX(mStartX);
        this.setY(mStartY);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
        }
    }

    public void setOnAnimEndListener(OnAnimEndListener onAnimEndListener) {
        mOnAnimEndListener = onAnimEndListener;
    }

    public interface OnAnimEndListener {
        void onAnimEnd();
    }

}