package com.example.dentalprofileapp.profile.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_dental_images_table",
        foreignKeys = {
                @ForeignKey(
                        entity = Patient.class,
                        parentColumns = {"mPatientId"},
                        childColumns = {"fkPatientId"}
                )})
public class PatientDentalImages {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int fkPatientId;
    private String urlUpperOcclusal;
    private String urlFrontFace;
    private String urlLowerOcclusal;
    private String urlFront;
    private String urlRightBuccal;
    private String urlLeftBuccal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(int fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public String getUrlUpperOcclusal() {
        return urlUpperOcclusal;
    }

    public void setUrlUpperOcclusal(String urlUpperOcclusal) {
        this.urlUpperOcclusal = urlUpperOcclusal;
    }

    public String getUrlFrontFace() {
        return urlFrontFace;
    }

    public void setUrlFrontFace(String urlFrontFace) {
        this.urlFrontFace = urlFrontFace;
    }

    public String getUrlLowerOcclusal() {
        return urlLowerOcclusal;
    }

    public void setUrlLowerOcclusal(String urlLowerOcclusal) {
        this.urlLowerOcclusal = urlLowerOcclusal;
    }

    public String getUrlFront() {
        return urlFront;
    }

    public void setUrlFront(String urlFront) {
        this.urlFront = urlFront;
    }

    public String getUrlRightBuccal() {
        return urlRightBuccal;
    }

    public void setUrlRightBuccal(String urlRightBuccal) {
        this.urlRightBuccal = urlRightBuccal;
    }

    public String getUrlLeftBuccal() {
        return urlLeftBuccal;
    }

    public void setUrlLeftBuccal(String urlLeftBuccal) {
        this.urlLeftBuccal = urlLeftBuccal;
    }
}