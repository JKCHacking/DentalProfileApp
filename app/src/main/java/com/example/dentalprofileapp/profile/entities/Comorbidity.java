package com.example.dentalprofileapp.profile.entities;

import androidx.room.ForeignKey
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comorbidity_table_trial_1",
        foreignKeys = {
                @ForeignKey(
                        entity = Patient.class,
                        parentColumns = {"mPatientId"}
                        childColumns = {"mFkPatientId"}
                )})
public class Comorbidity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int mFkPatientId;
    private String mComorbidityName;

    public Comorbidity(int patientId, String comorbidityName) {
        mPatientId = patientId;
        mComorbidityName = comorbidityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmFkPatientId() {
        return mFkPatientId;
    }

    public void setmFkPatientId(int mPatientId) {
        this.mFkPatientId = mPatientId;
    }

    public String getmComorbidityName() {
        return mComorbidityName;
    }

    public void setmComorbidityName(String mComorbidityName) {
        this.mComorbidityName = mComorbidityName;
    }
}