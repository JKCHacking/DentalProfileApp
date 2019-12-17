package com.example.dentalprofileapp.profile.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dentalprofileapp.profile.entities.PatientDentalImages;

import java.util.List;

@Dao
public interface PatientDentalImagesDao {
    @Insert
    void insert(PatientDentalImages patientDentalImages);

    @Query("SELECT * FROM patient_dental_images_table WHERE fkPatientId = :patientId")
    public abstract List<PatientDentalImages> getAllDentalImagesByPatientId(int patientId);
}