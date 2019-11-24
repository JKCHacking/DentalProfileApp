package com.example.dentalprofileapp.profile.repository;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table")
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int profilePicture;
    private String patientName;
    private String barangay;

    public Patient(int profilePicture, String patientName, String barangay) {
        this.profilePicture = profilePicture;
        this.patientName = patientName;
        this.barangay = barangay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getProfilePicture() {
        return this.profilePicture;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public String getBarangay() {
        return this.barangay;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }
}
