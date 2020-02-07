package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.google.firebase.auth.FirebaseAuth;

public class DentistCheckUpViewModel extends AndroidViewModel {

    //Repository
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;
    private FirebaseAuth firebaseAuth;

    //patient attributes
    private MutableLiveData<String> patientName;
    private MutableLiveData<String> patientId;


    public DentistCheckUpViewModel(@NonNull Application application) {
        super(application);
        firebaseAuth = FirebaseAuth.getInstance();
        patientRepository = new PatientRepository(application);
    }


}
