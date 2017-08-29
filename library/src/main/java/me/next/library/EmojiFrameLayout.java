package me.next.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NeXT on 17/8/29.
 */

public class EmojiFrameLayout extends FrameLayout {

    private static int EMOJI_COUNT = 20;
    private static int MAX_DURATION = 1200;

    private List<Drawable> mDrawableList = new ArrayList<>();

    public EmojiFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmojiFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public EmojiFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        //TODO CUSTOM PARAMS
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
