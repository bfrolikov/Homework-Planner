package com.example.bfrol.homeworkplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class ScheduleFragment extends Fragment {
    RecyclerView recyclerView;
    RecAdapter recAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = ((NumberedButton)v).getId();
                Intent startSelectSubjectActivity = new Intent(getContext(),SelectSubjectActivity.class);
                startActivityForResult(startSelectSubjectActivity,id);
            }
        };
        recyclerView = view.findViewById(R.id.schedule_recycler_view);
        recAdapter = new RecAdapter(((App)getActivity().getApplication()).getSchedule(),listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(recAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null)
        {
            String subjectName = data.getStringExtra("subject_name");
            ((App)getActivity().getApplication()).appendSubjectToDay(subjectName,requestCode);
            recAdapter.notifyDataSetChanged();
        }
    }
}
