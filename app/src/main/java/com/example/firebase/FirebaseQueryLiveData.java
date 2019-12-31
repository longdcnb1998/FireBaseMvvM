package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {

    private DatabaseReference reference;
    private MyValueEventListener listener = new MyValueEventListener();

    public FirebaseQueryLiveData(DatabaseReference reference) {
        this.reference =reference;
    }

    @Override
    protected void onActive() {
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        reference.removeEventListener(listener);
    }

    private class MyValueEventListener implements ValueEventListener{

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    }
}
