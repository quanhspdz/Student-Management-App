package com.example.studentmanagement;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQL_Action {

    //get data from mysql database to arraylist
    public static ArrayList<StudentForm> getDataFromWeb(Context context, String url) {
        ArrayList<StudentForm> arrayStudent = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                StudentForm student = new StudentForm(object.getInt("id"),
                                        object.getString("name"),
                                        object.getInt("yearBirth"),
                                        object.getString("address"));
                                arrayStudent.add(student);
                            } catch (JSONException e) {
                                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }

                        }
                        StudentHolder.arrayStudent = arrayStudent;
                        Refresh_Student_List.refreshList(context, arrayStudent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);

        return arrayStudent;
    }

    //function update data to database
    public static void updateStudentData(Context context, StudentForm student, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(context, "Update successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Update failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("id", String.valueOf(student.getId()));
                param.put("name", student.getName());
                param.put("birthYear", String.valueOf(student.getBirthYear()));
                param.put("address", student.getAddress());

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    //function add student to database
    public static void addStudentToDB(Context context, String url, StudentForm student) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().equals("success")) {
                            Toast.makeText(context, "Add successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Add failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
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

    //function delete student in database
    public static void deleteStudent(Context context, StudentForm student, String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().equals("success")) {
                            Toast.makeText(context, "Delete successfully!", Toast.LENGTH_SHORT).show();
                            MySQL_Action.getDataFromWeb(context, UrlHolder.getDataUrl);
                        }
                        else {
                            Toast.makeText(context, "Delete failure!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("id", String.valueOf(student.getId()));

                return param;
            }
        };

        requestQueue.add(stringRequest);
    }

}
