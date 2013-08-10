package com.resto.models;

import android.widget.Button;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.resto.R;

public class FooterButton extends Button {

public FooterButton (Context context) {
    super(context);
    setFooterAttributes();
}

public FooterButton (Context context, AttributeSet attrs) {
    super(context, attrs);
    setFooterAttributes();
}

public FooterButton (Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    setFooterAttributes();
    }

public void setFooterAttributes(){
    this.setTextSize(12);
    this.setLines(1);
    this.setBackgroundResource(R.drawable.button_bar_background);
    this.setHorizontallyScrolling(true);
    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(70 , 40);
    lp.setMargins(-3, -3, -3, -3);
    lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    this.setLayoutParams(lp);
}
}