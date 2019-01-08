package com.example.bfrol.homeworkplanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.util.List;

public class RecAdapter extends RecyclerView.Adapter <RecHolder> {
    private List<List<String>> schedule;
    private View.OnClickListener listener;
    public RecAdapter(List<List<String>> schedule, View.OnClickListener listener) {
        this.schedule = schedule;
        this.listener = listener;
    }
    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ScrollView sc = new ScrollView(viewGroup.getContext());
        ScrollView.LayoutParams layoutParams= new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.MATCH_PARENT);
        sc.setLayoutParams(layoutParams);
        return new RecHolder(sc,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder recHolder, int i) {
        recHolder.bind(schedule.get(i),i);
    }

    @Override
    public int getItemCount() {
        return schedule.size();
    }
}
