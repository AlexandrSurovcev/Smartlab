package com.example.smartlab;

import android.content.Context;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class EnableTextView {
    public void setAble(TextView textView1, TextView textView2, TextView textView3, Context context){
        onEnable(textView1,context);
        onDisable(textView2, context);
        onDisable(textView3, context);

    }
    public void onEnable(TextView textView, Context context){
        textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        textView.setBackgroundResource(R.drawable.enabledtextview);
    }
    public void onDisable(TextView textView, Context context){
        textView.setTextColor(ContextCompat.getColor(context, R.color.hintcolor));
        textView.setBackgroundResource(R.drawable.disabledtextview);
    }

    public void onEnableBtn(TextView textView, Context context){
        textView.setBackgroundResource(R.drawable.enabledbutton);
    }
    public void onDisableBtn(TextView textView, Context context){
        textView.setBackgroundResource(R.drawable.disabledbutton);
    }
}
