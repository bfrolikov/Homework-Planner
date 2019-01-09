package com.example.bfrol.homeworkplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private List<String> taskList;
    private List<List<String>> scheduleList;
    public Adapter(List<String> taskList, List<List<String>> scheduleList) {
        this.taskList = taskList;
        this.scheduleList = scheduleList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public String getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View task_view;
        if(convertView!=null)
            task_view = convertView;
        else
            task_view = inflater.inflate(R.layout.subject_item,parent,false);
        TextView subjectText = task_view.findViewById(R.id.subject_text);
        subjectText.setText(getItem(position));
        return task_view;
    }
}
