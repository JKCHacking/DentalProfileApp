package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.example.dentalprofileapp.profile.view.AddPatientActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class PatientListViewModel extends AndroidViewModel {

    //Repository
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;

    private MutableLiveData<List<Patient>> allPatientsMutableData;
    private MutableLiveData<String> sortBy;
    private MutableLiveData<String> searchBy;

    private FirebaseAuth firebaseAuth;

    public PatientListViewModel(@NonNull Application application) {
        super(application);

        firebaseAuth = FirebaseAuth.getInstance();
        patientRepository = new PatientRepository(application, this);
        comorbidityRepository = new ComorbidityRepository(application);
        patientDentalImagesRepository = new PatientDentalImagesRepository(application);
        sortBy = new MutableLiveData<>();
        searchBy = new MutableLiveData<>();
        allPatientsMutableData = new MutableLiveData<>();

        sortBy.setValue("Patient Name");
        searchBy.setValue("Patient Name");
        allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderPatientName());
    }

    public void setAllPatients() {
        if (sortBy.getValue().equals("Patient Name")) {
            allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderPatientName());
        } else if (sortBy.getValue().equals("Patient ID")) {
            allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderPatientId());
        } else if (sortBy.getValue().equals("Barangay")) {
            allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderBarangay());
        }
    }

    public MutableLiveData<String> getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(MutableLiveData<String> searchBy) {
        this.searchBy = searchBy;
    }

    public MutableLiveData<String> getSortBy() {
        return sortBy;
    }

    public MutableLiveData<List<Patient>> getAllPatientsMutableData() {
        return allPatientsMutableData;
    }

    public void setAllPatientsMutableData(MutableLiveData<List<Patient>> allPatientsMutableData) {
        this.allPatientsMutableData = allPatientsMutableData;
    }

    public void setSortBy(MutableLiveData<String> sortBy) {
        this.sortBy = sortBy;
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
        setAllPatients();
    }

    public void deleteAllPatients() {
        patientRepository.deleteAllPatients();
    }

    public void onClickAddPatient(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, AddPatientActivity.class);
        context.startActivity(intent);
    }
}
