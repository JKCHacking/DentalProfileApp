package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityAddPatientBinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.viewmodel.AddPatientViewModel;

public class AddPatientActivity extends AppCompatActivity {

    ActivityAddPatientBinding activityAddPatientBinding;
    AddPatientViewModel addPatientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityAddPatientBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_patient);
        addPatientViewModel = ViewModelProviders.of(this).get(AddPatientViewModel.class);

        activityAddPatientBinding.setViewmodel(addPatientViewModel);
        activityAddPatientBinding.setLifecycleOwner(this);

        addPatientViewModel.getPatientHighestId().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                addPatientViewModel.getPatientId().setValue(Integer.toString(patient.getId() + 1));
            }
        });
    }
}
