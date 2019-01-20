package com.example.bfrol.homeworkplanner;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
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
        //TODO чтение из sharedPreferences
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
