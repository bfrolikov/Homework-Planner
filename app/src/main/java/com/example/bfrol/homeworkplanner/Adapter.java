package com.example.bfrol.homeworkplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private List<String> subjectList;

    public Adapter(List<String> sl) {
        subjectList = sl;
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public String getItem(int position) {
        return subjectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void appendString(String s)
    {
        subjectList.add(s);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View subject_view;
        if(convertView!=null)
            subject_view = convertView;
        else
            subject_view = inflater.inflate(R.layout.subject_item,parent,false);
        TextView subjectText = subject_view.findViewById(R.id.subject_text);
        subjectText.setText(getItem(position));
        return subject_view;
    }
}
