package eu.bufa.prodan.myapplication.text_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ebs.psalms.R;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputPasswordWithValidation extends LinearLayout {
    @BindView(R.id.input_password)
    ViewPasswordInput inputPassword;
    @BindView(R.id.password_field_label)
    TextViewStyled passwordFieldLabel;
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;

    private int inputLabelTextColor;
    private int inputBackgroundTine;
    private int inputTextColor;
    private int inputType;
    private boolean showRevealIcon;
    private Drawable showPassIcon;
    private Drawable hidePassIcon;

    public InputPasswordWithValidation(Context context) {
        super(context);
        init(null);
    }

    public InputPasswordWithValidation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InputPasswordWithValidation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputPasswordWithValidation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.view_password_with_validation, this);
        ButterKnife.bind(this,view);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.InputPasswordWithValidation,
                    0, 0);

            try {
                inputLabelTextColor = a.getColor(R.styleable.InputPasswordWithValidation_passwordLabelColor, ContextCompat.getColor(getContext(),R.color.inputLabelColor));
                inputTextColor = a.getColor(R.styleable.InputPasswordWithValidation_passwordTextColor, ContextCompat.getColor(getContext(), R.color.editTextColor));
                showRevealIcon = a.getBoolean(R.styleable.InputPasswordWithValidation_showRevealIcon, true);
                hidePassIcon = a.getDrawable(R.styleable.InputPasswordWithValidation_hidePassIcon);
                showPassIcon= a.getDrawable(R.styleable.InputPasswordWithValidation_showPassIcon);
            } finally {
                a.recycle();
            }
        }
        inputPassword.setTextColor(inputTextColor);
        passwordFieldLabel.setTextColor(inputLabelTextColor);
        textInputLayout.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf"));
        inputPassword.setOnFocusChangeListener(onFocusChangeListener);
        if(!showRevealIcon)
            inputPassword.removeDrawables();
        else {
            if(showPassIcon != null && hidePassIcon!= null){
                inputPassword.setRevealDrawables(showPassIcon, hidePassIcon);
            }
        }
    }

    public void setLabelText(String text){
        passwordFieldLabel.setText(text);
    }

    public void showErrorMessage(String errorMessage){
        textInputLayout.setError(StringUtils.capitalize(errorMessage));
        textInputLayout.setErrorEnabled(true);
    }

    public void hiderErrorMessage(){
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }

    public String getInputText(){
        return inputPassword.getText().toString();
    }

    OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                if(textInputLayout.isErrorEnabled()){
                    hiderErrorMessage();
                }
            }
        }
    };

    public void setInputDefaultText(String text){
        inputPassword.setText(text);
    }

    public void addTextWatcher(TextWatcher textWatcher){
        inputPassword.addTextChangedListener(textWatcher);
    }
}
