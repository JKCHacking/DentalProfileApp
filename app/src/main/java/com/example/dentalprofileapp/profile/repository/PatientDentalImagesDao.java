package com.example.dentalprofileapp.profile.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dentalprofileapp.profile.entities.PatientDentalImages;

@Dao
public interface PatientDentalImagesDao {
    @Insert
    void insert(PatientDentalImages patientDentalImages);

    @Query("DELETE FROM patient_dental_images_table WHERE fkPatientId = :patientId")
    void deleteByPatientId(int patientId);

    @Query("SELECT * FROM patient_dental_images_table WHERE fkPatientId = :patientId")
    PatientDentalImages getPatientByPatientId(int patientId);
}