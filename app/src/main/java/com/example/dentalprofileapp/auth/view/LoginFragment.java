package com.example.dentalprofileapp.auth.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

    Context m_Context;
    ToastUtil m_toastUtil;
    LayoutLoginBinding layoutLoginBinding;
    AuthViewModel authViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutLoginBinding = DataBindingUtil.inflate(inflater, R.layout.layout_login, container, false);
        return layoutLoginBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        authViewModel = ViewModelProviders.of(getActivity()).get(AuthViewModel.class);
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        m_Context = context;
        m_toastUtil = new ToastUtil(m_Context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m_Context = null;
    }
}
