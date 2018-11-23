package eu.bufa.prodan.myapplication.text_views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import eu.bufa.prodan.myapplication.R;

public class ViewPasswordInput extends EditTextStyled {
    private boolean isPasswordVisible = false;
    private Drawable onShownPasswordDrawable;
    private Drawable onHiddenPasswordDrawable;

    public ViewPasswordInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ViewPasswordInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.ViewPasswordInput,
                    0, 0);

            try {
                onShownPasswordDrawable = a.getDrawable(R.styleable.ViewPasswordInput_shownPassIcon);
                onHiddenPasswordDrawable = a.getDrawable(R.styleable.ViewPasswordInput_hiddenPassIcon);
            } finally {
                a.recycle();
            }
        }

        setRevealDrawables(onShownPasswordDrawable, onHiddenPasswordDrawable);

    }

    @SuppressLint("ClickableViewAccessibility")
    public void setRevealDrawables(Drawable onShowIcon, Drawable onHideIcon) {
        setCompoundDrawablesWithIntrinsicBounds(null, null, onHideIcon, null);
        setCompoundDrawablePadding(20);
        setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (getCompoundDrawables()[2] == null)
                        break;
                    if (event.getRawX() >= (getRight() - getCompoundDrawables()[2].getBounds().width())) {
                        if (!isPasswordVisible) {
                            setInputType(InputType.TYPE_CLASS_TEXT);
                            setCompoundDrawablesWithIntrinsicBounds(null, null, onShowIcon, null);
                            setSelection(getText().length());
                            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf"));
                        } else {
                            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            setCompoundDrawablesWithIntrinsicBounds(null, null, onHideIcon, null);
                            setSelection(getText().length());
                            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf"));
                        }
                    }


                    return false;
                case MotionEvent.ACTION_UP:
                    isPasswordVisible = !isPasswordVisible;
                    return false;
            }
            return false;
        });
    }

    public void removeDrawables() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
