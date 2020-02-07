package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityDentistCheckUpBinding;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.example.dentalprofileapp.profile.viewmodel.DentistCheckUpViewModel;

import java.util.ArrayList;

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

        final Bundle extras = getIntent().getExtras();
        String patientId = extras.getString("patientId");
        dentistCheckUpViewModel.getPatientData(patientId);

        dentistCheckUpViewModel.getPatientMutableLiveData()
                .observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                dentistCheckUpViewModel.populatePatientDataToView(patient);
            }
        });

        dentistCheckUpViewModel.getComorbidityMutableLiveData()
                .observe(this, new Observer<ArrayList<Comorbidity>>() {
            @Override
            public void onChanged(ArrayList<Comorbidity> comorbidities) {
                dentistCheckUpViewModel.populateComorbidityDataToView(comorbidities);
            }
        });

        dentistCheckUpViewModel.getPatientDentalImagesMutableLiveData()
                .observe(this, new Observer<PatientDentalImages>() {
            @Override
            public void onChanged(PatientDentalImages patientDentalImages) {
                dentistCheckUpViewModel.populateDentalImagesToView(patientDentalImages);
            }
        });
    }
}
