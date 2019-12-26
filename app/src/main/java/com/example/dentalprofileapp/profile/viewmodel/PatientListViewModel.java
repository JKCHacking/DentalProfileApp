package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.example.dentalprofileapp.profile.view.AddPatientActivity;

import java.util.List;

public class PatientListViewModel extends AndroidViewModel {

    //Repository
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;

    private LiveData<List<Patient>> allPatients;

    public PatientListViewModel(@NonNull Application application) {
        super(application);

        patientRepository = new PatientRepository(application);
        comorbidityRepository = new ComorbidityRepository(application);
        patientDentalImagesRepository = new PatientDentalImagesRepository(application);

        allPatients = patientRepository.getAllPatients();
    }

    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }

    public void update(Patient patient) {
        patientRepository.update(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    public void deleteByPatientId(int patientId) {
        comorbidityRepository.deleteByPatientId(patientId);
        patientDentalImagesRepository.deleteByPatientId(patientId);
        patientRepository.deleteByPatientId(patientId);

        //this will update the record in recyclerview
        allPatients = patientRepository.getAllPatients();
    }

    public void deleteAllPatients() {
        patientRepository.deleteAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatients;
    }


    public void onClickAddPatient(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, AddPatientActivity.class);
        context.startActivity(intent);
    }
}
