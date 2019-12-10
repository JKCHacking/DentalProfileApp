package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.example.dentalprofileapp.profile.view.AddPatientActivity;

import java.util.List;

public class PatientListViewModel extends AndroidViewModel {
    private PatientRepository repository;
    private LiveData<List<Patient>> allPatients;

    public PatientListViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientRepository(application);
        allPatients = repository.getAllPatients();
    }

    public void insert(Patient patient) {
        repository.insert(patient);
    }

    public void update(Patient patient) {
        repository.update(patient);
    }

    public void delete(Patient patient) {
        repository.delete(patient);
    }

    public void deleteAllPatients() {
        repository.deleteAllPatients();
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
