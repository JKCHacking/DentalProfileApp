package com.example.dentalprofileapp.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.auth.viewmodel.AuthListener;
import com.example.dentalprofileapp.utils.ToastUtil;
import com.example.dentalprofileapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements AuthListener {

    ToastUtil m_toastUtil = new ToastUtil(this);
    ActivityLoginBinding activityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    public void onStarted() {
        m_toastUtil.createToastMessage("onStarted");
    }

    @Override
    public void onSuccess() {
        m_toastUtil.createToastMessage("onSuccess");
    }

    @Override
    public void onFailure(String message) {
        m_toastUtil.createToastMessage("message");
    }
}
