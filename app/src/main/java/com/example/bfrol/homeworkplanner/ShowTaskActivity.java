package com.example.bfrol.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTaskActivity extends AppCompatActivity {
    TextView showTaskText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        showTaskText = findViewById(R.id.show_task_text);
        Intent i  = getIntent();
        if(i!=null)
        {
            String tText = i.getStringExtra("show_task_text");
            showTaskText.setText(tText);
        }
    }

}
