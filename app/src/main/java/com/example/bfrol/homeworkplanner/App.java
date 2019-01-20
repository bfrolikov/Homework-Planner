package com.example.bfrol.homeworkplanner;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App extends Application {
    public static final String APP_PREFERENCES = "app_preferences";
    public static final String DAY = "day";
    public static final String SUBJECTS = "subjects";
    public static final String TASK = "task";
    public static final String TASKS_SIZE = "tasks_size";
    ArrayList<String> subjects;
    List<List<String>> schedule;
    ArrayList<String> tasks;

    public ArrayList<String> getTasks() {
        return tasks;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        subjects = new ArrayList<>();
        schedule = new ArrayList<>();
        tasks = new ArrayList<>();
        for(int i=0;i<7;i++) {
            schedule.add(new ArrayList<String>());
        }

        SharedPreferences sPreferences = getSharedPreferences(APP_PREFERENCES,Context.MODE_PRIVATE);
        for(int i=0;i<schedule.size();i++)
        {
            String savedSchedule = sPreferences.getString(DAY+Integer.toString(i),"");
            if(!savedSchedule.equals(""))
            {
                schedule.get(i).addAll(Arrays.asList(savedSchedule.split(",")));
            }
        }//reading schedule

        String savedSubjects = sPreferences.getString(SUBJECTS,"");
        if(!savedSubjects.equals(""))
        {
            subjects.addAll(Arrays.asList(savedSubjects.split(",")));
        }//reading subjects

        int tasksSize = sPreferences.getInt(TASKS_SIZE,0);
        for (int i = 0;i<tasksSize;i++)
        {
            tasks.add(sPreferences.getString(TASK+Integer.toString(i)," "));
        }//reading tasks

    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void appendSubject(String subject) {
        subjects.add(subject);
    }
    public void appendSubjectToDay(String subject,int day)
    {
        if(day<=6)
        {
            schedule.get(day).add(subject);
        }
    }

    public List<List<String>> getSchedule() {
        return schedule;
    }
}
