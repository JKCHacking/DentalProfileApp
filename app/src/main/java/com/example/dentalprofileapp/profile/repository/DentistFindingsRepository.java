package com.example.dentalprofileapp.profile.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DentistFindingsRepository {
    final static String DENTIST_FINDING_COLLECTION = "dentist_findings";
    final FirebaseFirestore firebaseFirestore;

    public DentistFindingsRepository() {
        this.firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void saveDentistFindings(DentistFinding dentistFinding, String patientName, String patientId) {
        String documentId = dentistFinding.getDentistName() + "_" + patientName + "_" + patientId;
        firebaseFirestore.collection(DENTIST_FINDING_COLLECTION)
                .document(documentId)
                .set(dentistFinding)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("Dentist Findings Successfully saved!");
                    }
                });
    }

    public void getDentistFindingsByPatientName(final String patientName,
                                                final MutableLiveData<List<DentistFinding>> DentistFindingsLiveData) {
        final List<DentistFinding> dentistFindingsList = new ArrayList<>();
        firebaseFirestore.collection(DENTIST_FINDING_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            dentistFindingsList.clear();
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getString("patientName").equals(patientName)) {
                                    DentistFinding dentistFinding = new DentistFinding();
                                    dentistFinding.setPatientName(document.getString("patientName"));
                                    dentistFinding.setDentistName(document.getString("dentistName"));
                                    dentistFinding.setDentitionStatus((HashMap<String, String>)document.get("dentitionStatus"));
                                    dentistFinding.setPlaqueScore(document.getString("plaqueScore"));
                                    dentistFinding.setUrgencyOfTreatment(document.getString("urgencyOfTreatment"));

                                    dentistFindingsList.add(dentistFinding);
                                }
                            }

                            if(!dentistFindingsList.isEmpty()) {
                                DentistFindingsLiveData.setValue(dentistFindingsList);
                            }
                        }
                    }
                });
    }

    public void getDentistFindingsByDocumentId(String documentId, final MutableLiveData<DentistFinding> dentistFindingLiveData) {
        final DentistFinding dentistFinding = new DentistFinding();
        firebaseFirestore.collection(DENTIST_FINDING_COLLECTION).document(documentId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            dentistFinding.setPatientName(document.getString("patientName"));
                            dentistFinding.setDentistName(document.getString("dentistName"));
                            dentistFinding.setDentitionStatus((HashMap<String, String>) document.get("dentitionStatus"));
                            dentistFinding.setPlaqueScore(document.getString("plaqueScore"));
                            dentistFinding.setUrgencyOfTreatment(document.getString("urgencyOfTreatment"));
                        }

                        dentistFindingLiveData.setValue(dentistFinding);
                    }
                });
    }
}
