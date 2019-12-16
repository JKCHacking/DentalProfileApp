package com.example.dentalprofileapp.profile.repository;

@Dao
public interface PatientDentalImagesDao {
    @Insert
    void insert(PatientDentalImagesDao patientDentalImagesDao);

    @Query("SELECT * FROM patient_dental_images_table WHERE fkPatientId = :patientId")
    public abstract List<PatientDentalImages> getAllDentalImagesByPatientId(int patientId);
}