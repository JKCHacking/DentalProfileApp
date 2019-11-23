package com.example.dentalprofileapp.profile.repository;

public class PatientItem {
    private int mProfilePicture;
    private String mProfileName;
    private String mBarangayName;

    public PatientItem(int profilePicture, String profileName, String barangayName) {
        mProfilePicture = profilePicture;
        mProfileName = profileName;
        mBarangayName = barangayName;
    }

    public int getProfilePicture() {
        return mProfilePicture;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public String getBarangayName() {
        return mBarangayName;
    }
}
