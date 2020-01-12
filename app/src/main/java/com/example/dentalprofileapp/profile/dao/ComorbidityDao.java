package com.example.dentalprofileapp.profile.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.dentalprofileapp.profile.entities.Comorbidity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Dao
public abstract class ComorbidityDao {

    @Transaction
    public void insertBatch(ArrayList<Comorbidity> comorbidityList) {
        for (Comorbidity comorbidity: comorbidityList) {
            insert(comorbidity);
        }
    }

    @Transaction
    public void updateBatch(ArrayList<Comorbidity> comorbidityList) {
        for (Comorbidity comorbidity: comorbidityList) {
            update(comorbidity.getFkPatientId(), comorbidity.getComorbidityName());
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    public abstract void insert(Comorbidity comorbidity);

    @Query("DELETE FROM comorbidity_table WHERE fkPatientId = :patientId")
    public abstract void deleteByPatientId(int patientId);

    @Query("SELECT * FROM comorbidity_table WHERE fkPatientId = :patientId")
    public abstract List<Comorbidity> getComorbidityByPatientId(int patientId);

    @Delete
    public abstract void deleteComorbidities(ArrayList<Comorbidity> comorbidities);

    @Query("DELETE FROM comorbidity_table WHERE fkPatientId = :patientId and comorbidityName = :comorbidityName")
    public abstract int deleteByPatientIdComorbidityName(int patientId, String comorbidityName);

    @Query("UPDATE comorbidity_table SET fkPatientId = :patientId," +
            "comorbidityName = :comorbidityName " +
            "WHERE fkPatientId = :patientId")
    public abstract void update(int patientId, String comorbidityName);
}