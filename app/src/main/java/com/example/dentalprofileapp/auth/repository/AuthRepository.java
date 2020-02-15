package com.example.dentalprofileapp.auth.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.auth.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthRepository {
    final FirebaseDatabase database;
    final FirebaseAuth firebaseAuth;
    final FirebaseFirestore firebaseFirestore;

    public AuthRepository() {
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
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
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        final String userId = currentUser.getUid();
        user.setUserId(userId);
        firebaseFirestore.collection("users").document(userId)
        .set(user)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Successfully registered!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Something went wrong!");
            }
        });
    }

    public void getUser(String userId, final MutableLiveData<User> userMutableLiveData) {
        firebaseFirestore.collection("users").document(userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            User user = new User();
                            user.setUserId(document.getString("userId"));
                            user.setEmail(document.getString("email"));
                            user.setMobileNumber(document.getString("mobileNumber"));
                            user.setName(document.getString("name"));
                            user.setPassword(document.getString("password"));
                            user.setUserType(document.getString("userType"));

                            userMutableLiveData.setValue(user);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failed to getUserId");
                    }
                });
    }
}
