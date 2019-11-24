package com.example.dentalprofileapp.profile.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("DELETE FROM patient_table")
    void deleteAllPatients();

    @Query("SELECT * FROM patient_table ORDER BY barangay DESC")
    LiveData<List<Patient>> getAllPatients();

}
