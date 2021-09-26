package com.example.studentmanagement;

import android.content.Context;

import java.util.ArrayList;

public class Refresh_Student_List {

    public static void refreshList(Context context, ArrayList<StudentForm> arrayStudent) {
        MainActivity.adapter = new StudentAdapter(context, R.layout.each_item_in_list, arrayStudent);
        MainActivity.listViewStudent.setAdapter(MainActivity.adapter);
    }

}
