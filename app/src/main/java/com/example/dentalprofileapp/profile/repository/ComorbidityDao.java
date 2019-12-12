package com.example.dentalprofileapp.profile.repository;

@Dao
public abstract class ComorbidityDao {

    @Transaction
    public void insertBatch(ArrayList<Comorbidity> comorbidityList) {
        for (Comorbidity comorbidity: comorbidityList) {
            insert(comorbidity);
        }
    }

    @Insert
    public abstract void insert(Comorbidity comorbidity);

    @Query("SELECT * FROM comorbidity_table_trial_1 WHERE mFkPatientId = :patientId")
    public abstract List<Comorbidity> getAllComorbidityByPatientId(int patientId);
}