package com.example.dentalprofileapp.profile.repository;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.dentalprofileapp.profile.entities.Comorbidity;

import java.util.ArrayList;
import java.util.List;

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

    @Query("SELECT * FROM comorbidity_table_trial_1 WHERE fkPatientId = :patientId")
    public abstract List<Comorbidity> getAllComorbidityByPatientId(int patientId);
}