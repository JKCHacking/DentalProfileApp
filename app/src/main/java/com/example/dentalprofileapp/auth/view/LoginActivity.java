package com.example.dentalprofileapp.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity {

    ToastUtil m_toastUtil = new ToastUtil(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = new LoginFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragmentLogin, loginFragment);
        fragmentTransaction.commit();
        m_toastUtil.createToastMessage("Log-in Fragment Created");
    }
}
