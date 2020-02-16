package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.repository.DentistFindingsRepository;

import java.util.List;

public class DentistFindingViewModel extends AndroidViewModel {
    public MutableLiveData<List<DentistFinding>> dentistFindingListLiveData;
    private DentistFindingsRepository repository;

    public DentistFindingViewModel(@NonNull Application application) {
        super(application);
        dentistFindingListLiveData = new MutableLiveData<>();
        repository = new DentistFindingsRepository();
    }

    public void getDentistFindingByPatientName(String patientName) {
        repository.getDentistFindingsByPatientName(patientName, dentistFindingListLiveData);
    }
}
