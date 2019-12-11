package com.example.dentalprofileapp.profile.repository;

@Dao
public interface ComorbidityDao {
    @Insert
    void insert(Comorbidity comorbidity);

    @Query("SELECT * FROM comorbidity_table_trial_1 WHERE mFkPatientId = :patientId")
    List<Comorbidity> getAllComorbidityByPatientId(int patientId);
}