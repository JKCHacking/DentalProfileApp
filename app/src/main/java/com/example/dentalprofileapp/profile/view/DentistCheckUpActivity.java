package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.auth.entity.User;
import com.example.dentalprofileapp.databinding.ActivityDentistCheckUpBinding;
import com.example.dentalprofileapp.databinding.ExpandedDialogLayoutBinding;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.example.dentalprofileapp.profile.viewmodel.DentistCheckUpViewModel;

import java.util.ArrayList;

public class DentistCheckUpActivity extends AppCompatActivity {

    private DentistCheckUpViewModel dentistCheckUpViewModel;
    private ActivityDentistCheckUpBinding dentistCheckUpBinding;
    private ExpandedDialogLayoutBinding expandedDialogLayoutBinding;

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

        dentistCheckUpViewModel.urlExpandedImage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("Url Image: " + s);
                displayDialog(getApplicationContext());
            }
        });

        dentistCheckUpViewModel.userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                dentistCheckUpViewModel.composeDentistFinding(user);
            }
        });

        dentistCheckUpViewModel.dentistFindingLiveData.observe(this, new Observer<DentistFinding>() {
            @Override
            public void onChanged(DentistFinding dentistFinding) {
                dentistCheckUpViewModel.saveDentistFindingsData(dentistFinding);
            }
        });
    }

    public void displayDialog(Context context) {
        expandedDialogLayoutBinding = ExpandedDialogLayoutBinding.inflate(LayoutInflater.from(context));
        expandedDialogLayoutBinding.setViewmodel(dentistCheckUpViewModel);

        Dialog zoomImageDialog = new Dialog(this);
        zoomImageDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        zoomImageDialog.setContentView(expandedDialogLayoutBinding.getRoot());
        zoomImageDialog.show();
    }

}
