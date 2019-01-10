package com.example.bfrol.homeworkplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeworkFragment extends Fragment {
    FloatingActionButton fab;
    LinearLayout homeworkLinearLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homework,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        //TODO (1) Парсинг tasks и заполнение homeworklinearlayout подгруженными заданиями
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_homework);
        homeworkLinearLayout = view.findViewById(R.id.homework_linear_layout);
        ArrayList<String> tasksTemp  = ((App)getActivity().getApplication()).getTasks();
        for (String task : tasksTemp)
        {
            String [] taskAndName = task.split(",");
            addTaskToFragment(taskAndName[0],taskAndName[1]);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openAddTaskActivity = new Intent(getContext(),AddTaskActivity.class);
                startActivityForResult(openAddTaskActivity,0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        {
            final String subjectName = data.getStringExtra("subject_name");
            final String taskText = data.getStringExtra("task_text");
            addTaskToFragment(subjectName,taskText);
            addTaskToArray(subjectName,taskText);
        }
    }
    private void addTaskToFragment(String subjectName, final String taskText)
    {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.task_item,homeworkLinearLayout,false);
        TextView subjectNameTextView = v.findViewById(R.id.subject_text_task);
        TextView date = v.findViewById(R.id.date);
        String dataFormatPattern = "dd MMMM yyyy, EEEE";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPattern, Locale.getDefault());
        String dateString = simpleDateFormat.format(getSubjectDeadline(subjectName));
        date.setText("Срок сдачи: "+dateString);
        subjectNameTextView.setText(subjectName);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openShowTaskActivivty = new Intent(getContext(),ShowTaskActivity.class);
                openShowTaskActivivty.putExtra("show_task_text",taskText);
                startActivity(openShowTaskActivivty);
            }
        });
        homeworkLinearLayout.addView(v);
    }
    private void addTaskToArray(String subjectName, String taskText)
    {
        ((App)getActivity().getApplication()).getTasks().add(subjectName+","+taskText);
    }
    private Date getSubjectDeadline(String subjectName)
    {
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_WEEK)-2;
        if(today==-1)
            today=6;
        List<List<String>> scheduleTemp = ((App)getActivity().getApplication()).getSchedule();
        for(int i=today;i<scheduleTemp.size();++i)
        {
            if(scheduleTemp.get(i).contains(subjectName))
            {
                c.add(Calendar.DAY_OF_WEEK,i-today);
                return c.getTime();
            }
        }
        for(int i=0;i<=today;++i)
        {
            if(scheduleTemp.get(i).contains(subjectName))
            {
                int dayTemp;
                if (i==6)
                    dayTemp=1;
                else
                    dayTemp = i+2;
                c.add(Calendar.WEEK_OF_YEAR,1);
                c.set(Calendar.DAY_OF_WEEK,dayTemp);
                return c.getTime();
            }
        }
        return c.getTime();
    }
}
