package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityDentistResultBinding;
import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.viewmodel.DentistResultViewModel;

public class DentistResultActivity extends AppCompatActivity {

    ActivityDentistResultBinding activityDentistResultBinding;
    DentistResultViewModel dentistResultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();

        String patientId = extras.getString("patientId");
        String patientName = extras.getString("patientName");
        String dentistName = extras.getString("dentistName");

        activityDentistResultBinding = DataBindingUtil.setContentView(this, R.layout.activity_dentist_result);
        activityDentistResultBinding.setLifecycleOwner(this);

        dentistResultViewModel = ViewModelProviders.of(this).get(DentistResultViewModel.class);
        activityDentistResultBinding.setViewmodel(dentistResultViewModel);
        dentistResultViewModel.getDentistFindingsByDocumentId(dentistName, patientName, patientId);

        dentistResultViewModel.dentistFindingLiveData.observe(this, new Observer<DentistFinding>() {
            @Override
            public void onChanged(DentistFinding dentistFinding) {
                dentistResultViewModel.populateDentistResultToView(dentistFinding);
            }
        });
    }
}
