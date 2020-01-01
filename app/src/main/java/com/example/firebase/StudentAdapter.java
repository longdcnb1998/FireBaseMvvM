package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.databinding.ItemStudentBinding;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<Student> students;
    private Context context;

    public StudentAdapter() {
    }
    //    public StudentAdapter(ArrayList<Student> students, Context context) {
//        this.students = students;
//        this.context = context;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_student,parent,false);
        ViewHolder holder = new ViewHolder(binding);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder viewHolder = holder;
        viewHolder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    public void setStudents(List<Student> students){
        this.students = (ArrayList<Student>) students;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemStudentBinding binding;

        public ViewHolder(@NonNull ItemStudentBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }

        public void bindData(int position) {
            Student student = students.get(position);
            if (student != null){
                binding.name.setText(student.getName());
            }
        }
    }
}
