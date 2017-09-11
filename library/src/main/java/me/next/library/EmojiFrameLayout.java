package me.next.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by NeXT on 17/8/29.
 */

public class EmojiFrameLayout extends FrameLayout {

    private static final String TAG = EmojiFrameLayout.class.getSimpleName();
    private static int EMOJI_COUNT = 10;
    private static int EMOJI_SIZE = 26;
    private static int MAX_DURATION = 1200;

    private int mEmojiSize;
    private int mEmojiCount = EMOJI_COUNT;
    private int mWindowHeight;
    private int mWindowWidth;

    private List<Drawable> mDrawableList = new ArrayList<>();
    private List<ImageView> mImageViewList = new ArrayList<>();

    public EmojiFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public EmojiFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EmojiFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        //TODO CUSTOM PARAMS
        mEmojiSize = DisplayUtils.dp2px(context, EMOJI_SIZE);
    }

    public List<ImageView> generateEmojis() {

        List<Drawable> drawableList = getDrawableList();
        if (drawableList == null || drawableList.size() == 0) {
            Log.e(TAG, "Emoji list can not be null");
            return null;
        }

        mWindowHeight = getMeasuredHeight();
        if (mWindowHeight == 0) {
            mWindowHeight = DisplayUtils.getWindowHeight(getContext());
        }
        mWindowWidth = getMeasuredWidth();
        if (mWindowWidth == 0) {
            mWindowWidth = DisplayUtils.getWindowWidth(getContext());
        }

        mImageViewList.clear();

        for (int i = 0; i < mEmojiCount; i++) {
            final ParabolaImageView emoji = new ParabolaImageView(getContext());
            emoji.setImageDrawable(drawableList.get(i % drawableList.size()));
            LayoutParams layoutParams = new LayoutParams(mEmojiSize, mEmojiSize);
            layoutParams.topMargin = mWindowHeight + mEmojiSize / 2;
            layoutParams.leftMargin = mWindowWidth / 2 - mEmojiSize / 2;
            emoji.setLayoutParams(layoutParams);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                emoji.setElevation(100);
            }
            mImageViewList.add(emoji);
            addView(emoji);
            emoji.setOnAnimEndListener(new ParabolaImageView.OnAnimEndListener() {
                @Override
                public void onAnimEnd() {
                    removeView(emoji);
                }
            });

            final int delayTime = (int) (Math.abs(new Random().nextGaussian()) * 800);
            Log.e(TAG, "delayTime : " + delayTime);
            emoji.startAnim((int) (Math.abs(new Random().nextGaussian()) * mWindowWidth), mWindowHeight, delayTime);
        }
        return mImageViewList;
    }

    public void addEmoji(@DrawableRes int resId) {
        mDrawableList.add(ContextCompat.getDrawable(getContext(), resId));
    }

    public void addEmoji(@NonNull Drawable drawable) {
        mDrawableList.add(drawable);
    }

    public void addEmoji(@NonNull Bitmap bitmap) {
        mDrawableList.add(new BitmapDrawable(getResources(), bitmap));
    }

    public void removeEmojis() {
        mDrawableList.clear();
    }

    public List<Drawable> getDrawableList() {
        return mDrawableList;
    }

    public void setDrawableList(List<Drawable> drawableList) {
        mDrawableList = drawableList;
    }

}
