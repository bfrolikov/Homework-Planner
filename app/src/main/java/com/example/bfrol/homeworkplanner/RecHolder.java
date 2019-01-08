package com.example.bfrol.homeworkplanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class RecHolder extends RecyclerView.ViewHolder {
    private View.OnClickListener listener;
    public RecHolder(@NonNull ScrollView itemView, View.OnClickListener listener) {
        super(itemView);
        this.listener = listener;
    }
    public void bind(List<String> daySchedule,int pos)
    {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        LinearLayout layout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.VERTICAL);
        for(String s:daySchedule)
        {
            View v = inflater.inflate(R.layout.subject_item,layout,false);
            TextView subject_name = v.findViewById(R.id.subject_text);
            subject_name.setText(s);
            layout.addView(v);
        }
        NumberedButton addButton = new NumberedButton(itemView.getContext(),pos);
        addButton.setOnClickListener(listener);
        layout.addView(addButton);
        if(((ScrollView)itemView).getChildCount()>=1)
        {
            ((ScrollView)itemView).removeAllViews();
            ((ScrollView)itemView).addView(layout);
        }
        else
        {
            ((ScrollView)itemView).addView(layout);
        }

    }
}
