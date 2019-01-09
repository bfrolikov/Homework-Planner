package com.example.bfrol.homeworkplanner;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> adapter;
    Button addSubjectButton;
    Button selectOkButton;
    EditText taskText;
    private int currentSpinnerArrayPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        addSubjectButton = findViewById(R.id.actadd_add_subject_button);
        selectOkButton = findViewById(R.id.actadd_select_ok_button);
        spinner = findViewById(R.id.actadd_subjects);
        taskText = findViewById(R.id.task_text);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,((App)getApplication()).getSubjects());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSpinnerArrayPosition = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        addSubjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSubjectAddDialogue = new Intent(addSubjectButton.getContext(),SubjectAddActivity.class);
                startActivityForResult(openSubjectAddDialogue,0);
            }
        });
        selectOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnTask = new Intent();
                List<String> sNameTemp = ((App)getApplication()).getSubjects();
                if (currentSpinnerArrayPosition >= sNameTemp.size())
                    returnTask.putExtra("subject_name"," ");
                else
                    returnTask.putExtra("subject_name",sNameTemp.get(currentSpinnerArrayPosition));
                returnTask.putExtra("task_text",taskText.getText().toString());
                setResult(0,returnTask);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data!=null)
        {
            String subjectName = data.getStringExtra("name_tag");
            if (!subjectName.isEmpty())
            {
                ((App)getApplication()).appendSubject(subjectName);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
