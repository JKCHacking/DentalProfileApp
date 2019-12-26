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

    @Query("DELETE FROM patient_table WHERE mPatientId = :patientId")
    void deleteByPatientId(int patientId);

    @Query("DELETE FROM patient_table")
    void deleteAllPatients();

    @Query("SELECT * FROM patient_table ORDER BY mPatientName DESC")
    LiveData<List<Patient>> getAllPatients();

    @Query("SELECT * FROM patient_table ORDER BY id DESC LIMIT 0, 1")
    LiveData<Patient> getPatientWithHighestId();

    @Query("SELECT * FROM patient_table WHERE mPatientId = :patientId")
    Patient getPatientByPatientId(int patientId);
}
