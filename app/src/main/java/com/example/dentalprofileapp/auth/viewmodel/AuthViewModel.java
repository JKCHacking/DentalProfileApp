package com.example.dentalprofileapp.auth.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {
    String email;
    String password;
    AuthListener authListener = null;

    public void onClickLogIn(View view) {
        if (email.isEmpty() && password.isEmpty()) {
            authListener.onFailure("Please input your email or password");
        }

        authListener.onSuccess();
    }
}
