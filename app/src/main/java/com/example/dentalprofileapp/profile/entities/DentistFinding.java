package com.example.dentalprofileapp.profile.entities;

import java.util.HashMap;

public class DentistFinding {
    private String dentistName;
    private String patientName;
    private HashMap<String, String> dentitionStatus;
    private String plaqueScore;
    private String urgencyOfTreatment;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public HashMap<String, String> getDentitionStatus() {
        return dentitionStatus;
    }

    public void setDentitionStatus(HashMap<String, String> dentitionStatus) {
        this.dentitionStatus = dentitionStatus;
    }

    public String getPlaqueScore() {
        return plaqueScore;
    }

    public void setPlaqueScore(String plaqueScore) {
        this.plaqueScore = plaqueScore;
    }

    public String getUrgencyOfTreatment() {
        return urgencyOfTreatment;
    }

    public void setUrgencyOfTreatment(String urgencyOfTreatment) {
        this.urgencyOfTreatment = urgencyOfTreatment;
    }
}