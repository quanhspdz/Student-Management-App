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
import java.util.Locale;
import java.util.Map;

public class Activity_Add_Student extends AppCompatActivity {

    Button btnAddStudent, btnCancel;
    EditText edtAddName, edtAddBirth, edtAddAddress;
    final String url = "http://192.168.31.214/android-webservice/insertData.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnCancel = findViewById(R.id.btnCancel);
        edtAddName = findViewById(R.id.edtAddName);
        edtAddBirth = findViewById(R.id.edtAddBirthYear);
        edtAddAddress = findViewById(R.id.edtAddAddress);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentForm student = new StudentForm();
                String name = edtAddName.getText().toString();
                String birthYear = edtAddBirth.getText().toString();
                String address = edtAddAddress.getText().toString();

                if (!name.equals("") && !birthYear.equals("") && !address.equals("")) {
                    student = new StudentForm(-1, name, Integer.parseInt(birthYear), address);
                    addStudentToDB(url, student);
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

    private void addStudentToDB(String url, StudentForm student) {
        RequestQueue requestQueue = Volley.newRequestQueue(Activity_Add_Student.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().equals("success")) {
                            Toast.makeText(Activity_Add_Student.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(Activity_Add_Student.this, "Add failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity_Add_Student.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("name", student.getName());
                params.put("birthYear", String.valueOf(student.getBirthYear()));
                params.put("address", student.getAddress());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}