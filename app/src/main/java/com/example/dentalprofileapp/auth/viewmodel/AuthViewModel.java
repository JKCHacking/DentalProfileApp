package com.example.dentalprofileapp.auth.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.auth.entity.User;
import com.example.dentalprofileapp.auth.repository.AuthRepository;
import com.example.dentalprofileapp.profile.view.PatientListActivity;
import com.example.dentalprofileapp.utils.ToastUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthViewModel extends AndroidViewModel{
    public String email;
    public String password;
    public String name;
    public String phoneNumber;
    public String userType;

    public AuthListener authListener = null;
    private MutableLiveData<Boolean> isDisplayLoginFrame;
    private ToastUtil toastUtil;
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    private AuthRepository authRepository;

    public AuthViewModel(Application application) {
        super(application);
        toastUtil = new ToastUtil(application);
        authRepository = new AuthRepository();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void onClickLogIn(View view) {
        authListener.onStarted();
        final Context context = view.getContext();

        if ((email == null || email.isEmpty()) && (password == null || password.isEmpty()) ) {
            authListener.onFailure("Please input your email or password");
        }

        final User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setMobileNumber(phoneNumber);
        user.setUserType(userType);
        user.setPassword(password);

        Task<AuthResult> loginUserResult = authRepository.loginUser(user);
        loginUserResult.addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    toastUtil.createToastMessage("Login Successful");
                    // call PatientListActivity
                    userMutableLiveData.setValue(user);
                } else {
                    toastUtil.createToastMessage("Username or password is incorrect");
                }
            }
        });

        authListener.onSuccess();
    }

    public void onClickRegister(View view) {
        Context context = view.getContext();


        authListener.onStarted();
        if (email.isEmpty() && password.isEmpty()) {
            authListener.onFailure("Please input your email or password");
        }

        final User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setMobileNumber(phoneNumber);
        user.setUserType(userType);
        user.setPassword(password);

        Task<AuthResult> saveUserResult = authRepository.registerUser(user);
        saveUserResult.addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    toastUtil.createToastMessage("Registration successful");
                    //save the registration details
                    authRepository.saveUser(user);
                    isDisplayLoginFrame.postValue(true);
                } else {
                    toastUtil.createToastMessage("Registration Failed");
                }
            }
        });
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

    public Boolean checkSignedInUser() {
        return authRepository.checkSignedInUser();
    }
}
