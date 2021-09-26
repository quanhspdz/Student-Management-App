package com.example.studentmanagement;

import static com.example.studentmanagement.R.id.menu_addStudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

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

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ListView listViewStudent;
    public static StudentAdapter adapter;
    ArrayList<StudentForm> arrayStudent;
    final String url = UrlHolder.getDataUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewStudent = findViewById(R.id.listViewStudent);
        arrayStudent =  new ArrayList<>();
        adapter = new StudentAdapter(MainActivity.this, R.layout.each_item_in_list, arrayStudent);
        arrayStudent = MySQL_Action.getDataFromWeb(MainActivity.this, url);
        adapter.notifyDataSetChanged();
        listViewStudent.setAdapter(adapter);

    }

    //to show add menu on app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_addStudent: {
                Intent intent = new Intent(MainActivity.this, Activity_Add_Student.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_refresh: {
                arrayStudent = MySQL_Action.getDataFromWeb(MainActivity.this, url);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        arrayStudent = MySQL_Action.getDataFromWeb(MainActivity.this, url);
        super.onResume();
    }
}