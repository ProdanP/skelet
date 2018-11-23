package eu.bufa.prodan.myapplication.text_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.ebs.psalms.R;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InputTextWithValidation extends LinearLayout {
    @BindView(R.id.input_text)
    EditTextStyled inputText;
    @BindView(R.id.input_text_label)
    TextViewStyled inputTextLabel;
    @BindView(R.id.textInputLayout)
    TextInputLayout textInputLayout;

    private int inputLabelTextColor;
    private int inputBackgroundTine;
    private int inputTextColor;
    private int inputType;
    private boolean isEditable = true;
    public InputTextWithValidation(Context context) {
        super(context);
        init(null);
    }

    public InputTextWithValidation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public InputTextWithValidation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputTextWithValidation(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.view_input_text_with_validation, this);
        ButterKnife.bind(this,view);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.InputTextWithValidation,
                    0, 0);

            try {
                inputLabelTextColor = a.getColor(R.styleable.InputTextWithValidation_inputLabelTextColor, ContextCompat.getColor(getContext(),R.color.inputLabelColor));
                inputTextColor = a.getColor(R.styleable.InputTextWithValidation_inputTextColor, ContextCompat.getColor(getContext(), R.color.editTextColor));
                inputBackgroundTine = a.getColor(R.styleable.InputTextWithValidation_inputBackgroundTint, ContextCompat.getColor(getContext(),R.color.editTextLineTintColor));
                inputType = a.getInt(R.styleable.InputTextWithValidation_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_NORMAL);
                isEditable = a.getBoolean(R.styleable.InputTextWithValidation_isEditable, true);
            } finally {
                a.recycle();
            }
        }
        inputText.setTextColor(inputTextColor);
        inputText.setInputType(inputType);
        inputTextLabel.setTextColor(inputLabelTextColor);
        textInputLayout.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Regular.ttf"));
        if(!isEditable){
            disableEdit();
        }
    }

    private void disableEdit(){
        inputText.setOnFocusChangeListener(onFocusChangeListener);
        inputText.setClickable(false);
        inputText.setOnLongClickListener(null);
        inputText.setCursorVisible(false);
        inputText.setFocusable(false);
        inputText.setFocusableInTouchMode(false);
        inputText.setKeyListener(null);
    }

    public void setLabelText(String text){
        inputTextLabel.setText(text);
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
        return inputText.getText().toString();
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
        inputText.setText(text);
    }

    public void setMaxInputLenght(int max){
        inputText.setFilters(new InputFilter[] {new InputFilter.LengthFilter(max)});
    }

    public void setLines(int lines){
        inputText.setLines(lines);
    }

    public void setGravityTopStart(){
        inputText.setGravity(Gravity.TOP|Gravity.START);
    }

    public void setLabelTextSize(float textSize){
        inputTextLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void boldFieldLabel(){
        inputTextLabel.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Montserrat-SemiBold.ttf"));
    }

    public void setCapitalizeFirstLetter(){
        inputText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
    }

    /*public void setCapitalizeFirstLetter(){
        InputFilter[] editFilters = inputText.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters, 0, newFilters, 0, editFilters.length);
        newFilters[editFilters.length] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.length() == 0){
                    if(dstart == 0){
                        return StringUtils.capitalize(source.toString());
                    } else
                        return source;
                } else
                    return String.valueOf(source.charAt(source.length()-1));
            }
        };

        inputText.setFilters(newFilters);
    }*/
}
