package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityDentistCheckUpBinding;
import com.example.dentalprofileapp.profile.viewmodel.DentistCheckUpViewModel;

public class DentistCheckUpActivity extends AppCompatActivity {

    private DentistCheckUpViewModel dentistCheckUpViewModel;
    private ActivityDentistCheckUpBinding dentistCheckUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dentistCheckUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_dentist_check_up);
        dentistCheckUpBinding.setLifecycleOwner(this);
        dentistCheckUpViewModel = ViewModelProviders.of(this).get(DentistCheckUpViewModel.class);
        dentistCheckUpBinding.setViewmodel(dentistCheckUpViewModel);
    }
}
