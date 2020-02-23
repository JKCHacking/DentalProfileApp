package com.example.dentalprofileapp.profile.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class PatientOnlineModel {
    private String age;
    private String allergies;
    private String barangay;
    private ArrayList<String> comorbidities;
    private HashMap<String, String> dentalImages;
    private String occupation;
    private int patientId;
    private String patientName;
    private Boolean pregnant;
    private String purok;
    private String registeredDate;
    private String sex;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public ArrayList<String> getComorbidities() {
        return comorbidities;
    }

    public void setComorbidities(ArrayList<String> comorbidities) {
        this.comorbidities = comorbidities;
    }

    public HashMap<String, String> getDentalImages() {
        return dentalImages;
    }

    public void setDentalImages(HashMap<String, String> dentalImages) {
        this.dentalImages = dentalImages;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    public String getPurok() {
        return purok;
    }

    public void setPurok(String purok) {
        this.purok = purok;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
