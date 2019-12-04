package com.example.dentalprofileapp.auth.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dentalprofileapp.profile.view.PatientListActivity;

public class AuthViewModel extends ViewModel {
    public String email;
    public String password;
    public AuthListener authListener = null;
    private MutableLiveData<Boolean> isDisplayLoginFrame;

    public void onClickLogIn(View view) {
        authListener.onStarted();
        if ((email == null || email.isEmpty()) && (password == null || password.isEmpty()) ) {
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

    public LiveData<Boolean> getIsDisplayLoginFrame() {
        if (isDisplayLoginFrame == null) {
            isDisplayLoginFrame = new MutableLiveData<>();
        }
        return isDisplayLoginFrame;
    }

    public void setLiveDataForRegistration(View view) {
        isDisplayLoginFrame.setValue(false);
    }

    public void setLiveDataForLogin(View view) {
        isDisplayLoginFrame.setValue(true);
    }

    public void onClickShowPatientRecords(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, PatientListActivity.class);
        context.startActivity(intent);
    }


}
