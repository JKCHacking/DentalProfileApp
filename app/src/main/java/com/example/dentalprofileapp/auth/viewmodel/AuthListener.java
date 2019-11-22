package com.example.dentalprofileapp.auth.viewmodel;

public interface AuthListener {
    void onStarted();
    void onSuccess();
    void onFailure(String message);
}
