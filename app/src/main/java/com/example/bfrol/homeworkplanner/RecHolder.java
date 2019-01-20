package com.example.bfrol.homeworkplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

class RecHolder extends RecyclerView.ViewHolder {
    private View.OnClickListener listener;
    private List<List<String>> schedule;
    private RecAdapter adapter;
    RecHolder(@NonNull ScrollView itemView, View.OnClickListener listener, List<List<String>> schedule,RecAdapter adapter) {
        super(itemView);
        this.listener = listener;
        this.schedule = schedule;
        this.adapter = adapter;
    }
    void bind(final int pos)
    {
        LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
        LinearLayout layout = new LinearLayout(itemView.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);
        List<String> daySchedule = schedule.get(pos);
        layout.setOrientation(LinearLayout.VERTICAL);//TODO (1) переделать разметку этого LinearLayout в xml-файл
        View dayName = inflater.inflate(R.layout.day_name,layout,false);
        TextView dayNameText = dayName.findViewById(R.id.day_name);
        switch (pos)
        {
            case 0:
                dayNameText.setText("Понедельник");
                break;
            case 1:
                dayNameText.setText("Вторник");
                break;
            case 2:
                dayNameText.setText("Среда");
                break;
            case 3:
                dayNameText.setText("Четверг");
                break;
            case 4:
                dayNameText.setText("Пятница");
                break;
            case 5:
                dayNameText.setText("Суббота");
                break;
            case 6:
                dayNameText.setText("Воскресенье");
                break;

        }
        layout.addView(dayName);
        for(int i = 0;i<daySchedule.size();++i)
        {
            View v = inflater.inflate(R.layout.subject_item,layout,false);
            TextView subject_name = v.findViewById(R.id.subject_text);
            subject_name.setText(daySchedule.get(i));
            final int iConst = i;
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Удалить предмет?").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            schedule.get(pos).remove(iConst);
                            adapter.notifyDataSetChanged();
                        }
                    }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    return true;
                }
            });
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
