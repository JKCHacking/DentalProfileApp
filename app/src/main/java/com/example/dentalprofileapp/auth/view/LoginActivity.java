package com.example.dentalprofileapp.auth.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.auth.entity.User;
import com.example.dentalprofileapp.auth.viewmodel.AuthViewModel;
import com.example.dentalprofileapp.databinding.ActivityLoginBinding;
import com.example.dentalprofileapp.profile.view.PatientListActivity;
import com.google.firebase.auth.FirebaseAuth;
//import com.example.dentalprofileapp.utils.ToastUtil;


public class LoginActivity extends AppCompatActivity {

//    ToastUtil m_toastUtil = new ToastUtil(this);
    private AuthViewModel authViewModel;
    public ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLifecycleOwner(this);

        final LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_layout_login, loginFragment);
        fragmentTransaction.commit();

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        activityLoginBinding.setViewmodel(authViewModel);

        final Observer<Boolean> isDisplayObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                RegisterFragment registerFragment = new RegisterFragment();

                if (!authViewModel.getIsDisplayLoginFrame().getValue()) {
                    fragmentTransaction.replace(R.id.fragment_layout_login, registerFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else {
                    fragmentTransaction.replace(R.id.fragment_layout_login, loginFragment);
                    fragmentTransaction.commit();
                }
            }
        };
        authViewModel.getIsDisplayLoginFrame().observe(this, isDisplayObserver);

        authViewModel.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                callPatientList();
            }
        });
    }

    private void callPatientList() {
        Intent intent = new Intent(this, PatientListActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(authViewModel.checkSignedInUser()) {
            callPatientList();
        }
    }
}
