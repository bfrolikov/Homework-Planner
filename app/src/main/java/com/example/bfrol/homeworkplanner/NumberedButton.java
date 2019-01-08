package com.example.bfrol.homeworkplanner;

import android.content.Context;
import android.widget.Button;

public class NumberedButton extends android.support.v7.widget.AppCompatButton {
    int id = 0;
    public NumberedButton(Context context) {
        super(context);
    }
    public NumberedButton(Context context,int id) {
        super(context);
        this.id = id;
        super.setText(Integer.toString(id));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
