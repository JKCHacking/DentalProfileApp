package com.example.dentalprofileapp.auth.repository;

import androidx.annotation.NonNull;
import com.example.dentalprofileapp.auth.entity.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AuthRepository {
    final FirebaseDatabase database;
    final FirebaseAuth firebaseAuth;

    public AuthRepository() {
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> loginUser(User user) {
        return firebaseAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword());
    }
    public Task<AuthResult> registerUser(User user) {
        return firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword());
    }
    public void logoutUser() {
        firebaseAuth.signOut();
    }

    public Boolean checkSignedInUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        boolean isSignedIn = false;

        if(currentUser != null) {
            isSignedIn = true;
        }
        return isSignedIn;
    }

    public void saveUser(final User user) {
        DatabaseReference ref = database.getReference();
        final DatabaseReference usersRef = ref.child("users");

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        final String userId = currentUser.getUid();
        user.setUserId(userId);

        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("Data on database has changed.");
                usersRef.child(userId).setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Writing Data has been cancelled.");
            }
        });
    }
}
