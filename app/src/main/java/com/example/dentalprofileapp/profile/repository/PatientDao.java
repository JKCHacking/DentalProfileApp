package com.example.dentalprofileapp.profile.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dentalprofileapp.profile.entities.Patient;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("DELETE FROM patient_table_trial_1")
    void deleteAllPatients();

    @Query("SELECT * FROM patient_table_trial_1 ORDER BY mPatientName DESC")
    LiveData<List<Patient>> getAllPatients();

    @Query("SELECT * FROM patient_table_trial_1 ORDER BY id DESC LIMIT 0, 1")
    LiveData<Patient> getPatientWithHighestId();

}
