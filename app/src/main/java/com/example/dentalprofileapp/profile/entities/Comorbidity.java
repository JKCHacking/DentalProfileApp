package com.example.dentalprofileapp.profile.entities;

import androidx.room.ForeignKey;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "comorbidity_table_trial_1",
        foreignKeys = {
                @ForeignKey(
                        entity = Patient.class,
                        parentColumns = {"mPatientId"},
                        childColumns = {"fkPatientId"}
                )},
        indices = {@Index(value = {"id", "fkPatientId"}, unique = true)})
public class Comorbidity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int fkPatientId;
    private String comorbidityName;

    public Comorbidity(int fkPatientId, String comorbidityName) {
        this.fkPatientId = fkPatientId;
        this.comorbidityName = comorbidityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(int mPatientId) {
        this.fkPatientId = mPatientId;
    }

    public String getComorbidityName() {
        return comorbidityName;
    }

    public void setComorbidityName(String comorbidityName) {
        this.comorbidityName = comorbidityName;
    }
}