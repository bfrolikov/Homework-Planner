package com.example.bfrol.homeworkplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    ArrayList<String> tasks;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homework,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        //Парсинг tasks и заполнение homeworklinearlayout подгруженными заданиями
        super.onViewCreated(view, savedInstanceState);
        fab = view.findViewById(R.id.fab_homework);
        homeworkLinearLayout = view.findViewById(R.id.homework_linear_layout);
        tasks = ((App)getActivity().getApplication()).getTasks();
        refillTasks();
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
            addTaskToFragment(subjectName,taskText,tasks.size());
            tasks.add(subjectName+","+taskText);//adding the created task to tasks array
        }
    }
    private void addTaskToFragment(String subjectName, final String taskText,final int taskIndex)
    {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.task_item,homeworkLinearLayout,false);
        TextView subjectNameTextView = v.findViewById(R.id.subject_text_task);
        TextView date = v.findViewById(R.id.date);
        String dataFormatPattern = "dd MMMM yyyy, EEEE";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatPattern, Locale.getDefault());
        String dateString;
        if(getSubjectDeadline(subjectName)!=null)
            dateString = "Срок сдачи: "+simpleDateFormat.format(getSubjectDeadline(subjectName));
        else
            dateString = "Добавьте предмет в расписание";

        date.setText(dateString);
        subjectNameTextView.setText(subjectName);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openShowTaskActivivty = new Intent(getContext(),ShowTaskActivity.class);
                openShowTaskActivivty.putExtra("show_task_text",taskText);
                startActivity(openShowTaskActivivty);
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Удалить задание?").setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tasks.remove(taskIndex);
                        refillTasks();
                    }
                }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        homeworkLinearLayout.addView(v);
    }
    @Nullable
    private Date getSubjectDeadline(String subjectName)
    {
        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_WEEK)-2;
        if(today==-1)
            today=6;
        List<List<String>> scheduleTemp = ((App)getActivity().getApplication()).getSchedule();
        for(int i=today+1;i<scheduleTemp.size();++i)
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
        return null;
    }
    private void refillTasks()
    {
        if(homeworkLinearLayout.getChildCount()>0)
            homeworkLinearLayout.removeAllViews();
        for (int i = 0;i<tasks.size();++i)
        {
            String [] taskAndName = tasks.get(i).split(",",2);
            addTaskToFragment(taskAndName[0],taskAndName[1],i);
        }
    }
}
