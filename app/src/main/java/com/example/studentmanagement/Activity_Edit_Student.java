package com.example.studentmanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Activity_Edit_Student extends AppCompatActivity {

    Button btnEdit, btnCancel;
    EditText edtName, edtBirth, edtAddress;
    final String url = UrlHolder.editUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        //view assignment
        btnEdit = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnEditCancel);
        edtName = findViewById(R.id.edtEditName);
        edtBirth = findViewById(R.id.edtEditBirth);
        edtAddress = findViewById(R.id.edtEditAddress);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //set form to show the information of chosen student
        StudentForm student = StudentHolder.currentStudent;
        if (student != null) {
            edtName.setText(student.getName());
            edtBirth.setText(String.valueOf(student.getBirthYear()));
            edtAddress.setText(student.getAddress());
        }
        else {
            Toast.makeText(Activity_Edit_Student.this, "current student is null!", Toast.LENGTH_SHORT).show();
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentForm student = new StudentForm();
                String id = String.valueOf(StudentHolder.currentStudent.getId());
                String name = edtName.getText().toString();
                String birthYear = edtBirth.getText().toString();
                String address = edtAddress.getText().toString();

                if (!name.equals("") && !birthYear.equals("") && !address.equals("")) {
                    student = new StudentForm(Integer.parseInt(id), name, Integer.parseInt(birthYear), address);
                    MySQL_Action.updateStudentData(Activity_Edit_Student.this, student, url);
                    finish();
                }
                else {
                    Toast.makeText(Activity_Edit_Student.this, "Please fill out all information!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}