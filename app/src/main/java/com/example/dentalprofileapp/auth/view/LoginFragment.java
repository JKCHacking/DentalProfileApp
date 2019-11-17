package com.example.dentalprofileapp.auth.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.auth.viewmodel.AuthListener;
import com.example.dentalprofileapp.auth.viewmodel.AuthViewModel;
import com.example.dentalprofileapp.databinding.LayoutLoginBinding;
import com.example.dentalprofileapp.utils.ToastUtil;

public class LoginFragment extends Fragment implements AuthListener {

    ToastUtil m_toastUtil = new ToastUtil(getActivity());
    LayoutLoginBinding layoutLoginBinding;
    AuthViewModel authViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeBindings();
    }

    private void initializeBindings() {
        layoutLoginBinding = DataBindingUtil.setContentView(getActivity(), R.layout.layout_login);

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        authViewModel.authListener = this;
        layoutLoginBinding.setViewmodel(authViewModel);
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
