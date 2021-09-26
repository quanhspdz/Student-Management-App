package com.example.studentmanagement;

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

public class Activity_Add_Student extends AppCompatActivity {

    Button btnAddStudent, btnCancel;
    EditText edtAddName, edtAddBirth, edtAddAddress;
    final String url = UrlHolder.addUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btnAddStudent = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnEditCancel);
        edtAddName = findViewById(R.id.edtEditName);
        edtAddBirth = findViewById(R.id.edtEditBirth);
        edtAddAddress = findViewById(R.id.edtEditAddress);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentForm student = new StudentForm();
                String name = edtAddName.getText().toString();
                String birthYear = edtAddBirth.getText().toString();
                String address = edtAddAddress.getText().toString();

                if (!name.equals("") && !birthYear.equals("") && !address.equals("")) {
                    student = new StudentForm(-1, name, Integer.parseInt(birthYear), address);
                    MySQL_Action.addStudentToDB(Activity_Add_Student.this, url, student);
                    finish();
                }
                else {
                    Toast.makeText(Activity_Add_Student.this, "Please fill out all information!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}