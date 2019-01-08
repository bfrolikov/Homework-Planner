package com.example.bfrol.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubjectAddActivity extends AppCompatActivity {
    Button addOkButton;
    EditText subjectNameEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add);
        addOkButton = findViewById(R.id.add_ok_button);
        subjectNameEdit = findViewById(R.id.subject_name_edit);
        addOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnName = new Intent();
                returnName.putExtra("name_tag",subjectNameEdit.getText().toString());
                setResult(0,returnName);
                finish();
            }
        });
    }
}
