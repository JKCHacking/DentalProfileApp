package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityAddPatientBinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.viewmodel.AddPatientViewModel;

public class AddPatientActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1889;

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

        addPatientViewModel.getOpenGalleryLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, GALLERY_REQUEST);
            }
        });
    }
}
