package com.example.bfrol.homeworkplanner;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectSubjectActivity extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<String> adapter;
    Button addSubjectButton;
    Button selectOkButton;
    private int currentSpinnerArrayPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        addSubjectButton = findViewById(R.id.add_subject_button);
        selectOkButton = findViewById(R.id.select_ok_button);
        spinner = findViewById(R.id.subjects);
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
                Intent returnSubject = new Intent();
                List<String> sNameTemp = ((App)getApplication()).getSubjects();
                if (currentSpinnerArrayPosition >= sNameTemp.size())
                    returnSubject.putExtra("subject_name"," ");
                else
                    returnSubject.putExtra("subject_name",sNameTemp.get(currentSpinnerArrayPosition));
                setResult(0,returnSubject);
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
