package com.example.dentalprofileapp.profile.repository;

import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DentistFindingsRepository {
    final FirebaseFirestore firebaseFirestore;

    public DentistFindingsRepository() {
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void saveDentistFindings(DentistFinding dentistFinding, String patientName, String patientId) {
        String documentId = dentistFinding.getDentistName() + "_" + patientName + "_" + patientId;
        firebaseFirestore.collection("dentist_findings")
                .document(documentId)
                .set(dentistFinding)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Dentist Findings Successfully saved!");
                    }
                });
    }
}
