package com.example.dentalprofileapp.profile.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table",
        indices = {
                @Index(value="id"),
                @Index(value="mPatientId", unique=true)
        })
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int mPatientId;
    private int profilePicture;
    private String mDate;
    private String mPatientName;
    private String mAge;
    private String mSex;
    private String mOccupation;
    private String mBarangay;
    private String mPurok;
    private String mAllergies;
    private Boolean mPregnant;


    public int getId() {
        return id;
    }

    public int getProfilePicture() {
        return this.profilePicture;
    }

    public String getPatientName() {
        return this.mPatientName;
    }

    public String getBarangay() {
        return this.mBarangay;
    }

    public int getPatientId() {
        return mPatientId;
    }

    public String getDate() {
        return mDate;
    }

    public String getAge() {
        return mAge;
    }

    public String getSex() {
        return mSex;
    }

    public String getOccupation() {
        return mOccupation;
    }

    public String getPurok() {
        return mPurok;
    }

    public String getAllergies() {
        return mAllergies;
    }

    public Boolean getPregnant() {
        return mPregnant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setPatientName(String patientName) {
        this.mPatientName = patientName;
    }

    public void setBarangay(String barangay) {
        this.mBarangay = barangay;
    }

    public void setPatientId(int mPatientId) {
        this.mPatientId = mPatientId;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public void setAge(String mAge) {
        this.mAge = mAge;
    }

    public void setSex(String mSex) {
        this.mSex = mSex;
    }

    public void setOccupation(String mOccupation) {
        this.mOccupation = mOccupation;
    }

    public void setPurok(String mPurok) {
        this.mPurok = mPurok;
    }

    public void setAllergies(String mAllergies) {
        this.mAllergies = mAllergies;
    }

    public void setPregnant(Boolean mPregnant) {
        this.mPregnant = mPregnant;
    }
}
