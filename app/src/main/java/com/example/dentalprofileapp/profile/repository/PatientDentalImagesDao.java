package com.example.dentalprofileapp.profile.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dentalprofileapp.profile.entities.PatientDentalImages;

@Dao
public interface PatientDentalImagesDao {
    @Insert
    long insert(PatientDentalImages patientDentalImages);

    @Query("DELETE FROM patient_dental_images_table WHERE fkPatientId = :patientId")
    void deleteByPatientId(int patientId);

    @Query("UPDATE patient_dental_images_table SET " +
            "fkPatientId = :patientId," +
            "urlUpperOcclusal = :urlUpperOcclusal," +
            "urlFrontFace = :urlFrontFace," +
            "urlLowerOcclusal = :urlLowerOcclusal," +
            "urlFront = :urlFront," +
            "urlRightBuccal = :urlRightBuccal," +
            "urlLeftBuccal = :urlLeftBuccal")
    void update(int patientId,
                String urlUpperOcclusal,
                String urlFrontFace,
                String urlLowerOcclusal,
                String urlFront,
                String urlRightBuccal,
                String urlLeftBuccal);

    @Query("SELECT * FROM patient_dental_images_table WHERE fkPatientId = :patientId")
    PatientDentalImages getPatientDentalImagesByPatientId(int patientId);
}