package com.example.dentalprofileapp.auth.viewmodel;

import android.view.View;

import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {
    public String email;
    public String password;
    public AuthListener authListener = null;

    public void onClickLogIn(View view) {
        authListener.onStarted();
        if (email.isEmpty() && password.isEmpty()) {
            authListener.onFailure("Please input your email or password");
        }
        authListener.onSuccess();
    }

    public void onClickRegister(View view) {
        authListener.onStarted();
        if (email.isEmpty() && password.isEmpty()) {
            authListener.onFailure("Please input your email or password");
        }
        authListener.onSuccess();
    }
}
