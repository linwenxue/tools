package com.lin.study.java.innerclass;

/**
 * Created by linwenxue on 2015/2/1.
 */
public class Button {
    private OnClickListener onClickListener;

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
