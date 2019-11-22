package com.example.dentalprofileapp.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.auth.viewmodel.AuthViewModel;
import com.example.dentalprofileapp.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity {

    ToastUtil m_toastUtil = new ToastUtil(this);
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_toastUtil.createToastMessage("onCreate() called");

        LoginFragment loginFragment = new LoginFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_layout_login, loginFragment);
        fragmentTransaction.commit();
        m_toastUtil.createToastMessage("Log-in Fragment Created");


        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);

        final Observer<Boolean> isDisplayObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // determine if live data is true or false
                // if isDisplayLoginFrame == true display login fragment
                // if isDisplayLoginFrame == false display register fragment
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.fragment_layout_login, registerFragment);
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();
            }
        };

        authViewModel.getIsDisplayLoginFrame().observe(this, isDisplayObserver);
    }
}
