package com.example.firebase;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentViewModel extends ViewModel {
    private static DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");


    private static FirebaseQueryLiveData liveData =  new FirebaseQueryLiveData(reference);

    private LiveData<List<Student>> data = Transformations.map(liveData,new Deserializer());


    private class Deserializer implements Function<DataSnapshot,List<Student>>{


        @Override
        public List<Student> apply(DataSnapshot input) {

            List<Student> students = new ArrayList<>();
            Log.d("AAA",input.toString());
            for (DataSnapshot snapshot : input.getChildren()){
                Student s = snapshot.getValue(Student.class);
                students.add(s);
            }

            return students;
        }
    }



    public LiveData<List<Student> >getData(){
        return data;
    }

    public void updateStudent(Student students){
        ArrayList<Student> list = (ArrayList<Student>) data.getValue();
        Log.d("LongDinh", String.valueOf(list.size()));
        list.add(students);
        reference.setValue(list);
    }
}
