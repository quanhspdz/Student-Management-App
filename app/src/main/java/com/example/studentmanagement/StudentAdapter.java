package com.example.studentmanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<StudentForm> arrayStudent;

    public StudentAdapter(Context context, int layout, ArrayList<StudentForm> arrayStudent) {
        this.context = context;
        this.layout = layout;
        this.arrayStudent = arrayStudent;
    }

    @Override
    public int getCount() {
        return arrayStudent.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtName, txtAddress, txtBirthYear;
        ImageButton imgBtnEdit, imgBtnDel;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtAddress = view.findViewById(R.id.txtAddress);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtBirthYear = view.findViewById(R.id.txtBirthYear);
            holder.imgBtnDel = view.findViewById(R.id.imgBtnDel);
            holder.imgBtnEdit = view.findViewById(R.id.imgBtnEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtName.setText(arrayStudent.get(i).getName());
        holder.txtAddress.setText(arrayStudent.get(i).getAddress());
        holder.txtBirthYear.setText(String.valueOf(arrayStudent.get(i).getBirthYear()));

        holder.imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Activity_Edit_Student.class);
                StudentHolder.currentStudent = arrayStudent.get(i);
                context.startActivity(intent);
            }
        });

        holder.imgBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteAlert(arrayStudent.get(i));
            }
        });

        return view;
    }
    public void showDeleteAlert(StudentForm student) {
        AlertDialog.Builder deleteAlertDialog = new AlertDialog.Builder(context);
        deleteAlertDialog.setMessage("Do you really want to delete " + student.getName() + " ?");
        deleteAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        deleteAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MySQL_Action.deleteStudent(context, student, UrlHolder.deleteUrl);
            }
        });

        deleteAlertDialog.show();

    }
}
