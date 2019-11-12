package com.example.dentalprofileapp.auth.viewmodel;

public interface AuthListener {
    public void onStarted();
    public void onSuccess();
    public void onFailure(String message);
}
