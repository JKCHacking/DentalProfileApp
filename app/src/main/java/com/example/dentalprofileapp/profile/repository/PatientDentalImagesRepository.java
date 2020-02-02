package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.example.dentalprofileapp.auth.repository.AuthRepository;
import com.example.dentalprofileapp.profile.dao.PatientDentalImagesDao;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class PatientDentalImagesRepository {

    private PatientDentalImagesDao patientDentalImagesDao;
    private AuthRepository authRepository;
    private FirebaseFirestore firebaseFirestore;
    private PatientDentalImages patientDentalImagesResult;

    public PatientDentalImagesRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDentalImagesDao = database.patientDentalImagesDao();
        authRepository = new AuthRepository();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void insert(PatientDentalImages patientDentalImages) {
        try {
            long returnedId = new PatientDentalImagesRepository
                    .InsertPatientDentalImagesAsyncTask(patientDentalImagesDao)
                    .execute(patientDentalImages)
                    .get();
            System.out.println(returnedId);
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public PatientDentalImages getPatientDentalImagesByPatientId(final int patientId){
        patientDentalImagesResult = null;

        try {
            if (authRepository.checkSignedInUser()){
                //do something to get the user from the firebase database.
                System.out.println("There is a signed in user!");
                CollectionReference patientsRef = firebaseFirestore.collection("patients");
                patientsRef.get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        if (document.getLong("patientId").intValue() == patientId ) {
                                            patientDentalImagesResult = new PatientDentalImages();
                                            Map<String, String> dentalImages = (Map<String, String>)document.get("dentalImages");
                                            for (Map.Entry<String, String> dentalImageEntry : dentalImages.entrySet()) {
                                                switch (dentalImageEntry.getKey()) {
                                                    case "front":
                                                        patientDentalImagesResult.setUrlFront(dentalImageEntry.getValue());
                                                        break;
                                                    case "frontFace":
                                                        patientDentalImagesResult.setUrlFrontFace(dentalImageEntry.getValue());
                                                        break;
                                                    case "leftBuccal":
                                                        patientDentalImagesResult.setUrlLeftBuccal(dentalImageEntry.getValue());
                                                        break;
                                                    case "lowerOcclusal":
                                                        patientDentalImagesResult.setUrlLowerOcclusal(dentalImageEntry.getValue());
                                                        break;
                                                    case "rightBuccal":
                                                        patientDentalImagesResult.setUrlRightBuccal(dentalImageEntry.getValue());
                                                        break;
                                                    case "upperOcclusal":
                                                        patientDentalImagesResult.setUrlUpperOcclusal(dentalImageEntry.getValue());
                                                        break;
                                                }
                                            }
                                            break;
                                        }
                                    }

                                    if (patientDentalImagesResult == null) {
                                        System.out.println("Cannot find patient with Patient Id " + patientId);
                                    }
                                } else {
                                    System.out.println("Error getting documents." + task.getException());
                                }
                            }
                        });
            } else {
                patientDentalImagesResult = new GetPatientDentalImagesByPatientIdAsyncTask(patientDentalImagesDao).execute(patientId).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return patientDentalImagesResult;
    }

    public void update(PatientDentalImages patientDentalImages) {
        new UpdateAsyncTask(patientDentalImagesDao).execute(patientDentalImages);
    }

    private static class UpdateAsyncTask extends AsyncTask<PatientDentalImages, Void, Void> {
        private PatientDentalImagesDao patientDentalImagesDao;

        public UpdateAsyncTask(PatientDentalImagesDao patientDentalImagesDao) {
            this.patientDentalImagesDao = patientDentalImagesDao;
        }

        @Override
        protected Void doInBackground(PatientDentalImages... patientDentalImages) {

            this.patientDentalImagesDao.update(
                    patientDentalImages[0].getFkPatientId(),
                    patientDentalImages[0].getUrlUpperOcclusal(),
                    patientDentalImages[0].getUrlFrontFace(),
                    patientDentalImages[0].getUrlLowerOcclusal(),
                    patientDentalImages[0].getUrlFront(),
                    patientDentalImages[0].getUrlRightBuccal(),
                    patientDentalImages[0].getUrlLeftBuccal());
            return null;
        }
    }

    private static class GetPatientDentalImagesByPatientIdAsyncTask extends AsyncTask<Integer, Void, PatientDentalImages> {
        private PatientDentalImagesDao patientDentalImagesDao;

        private GetPatientDentalImagesByPatientIdAsyncTask(PatientDentalImagesDao patientDentalImagesDao) {
            this.patientDentalImagesDao = patientDentalImagesDao;
        }

        @Override
        protected PatientDentalImages doInBackground(Integer... integers) {
            return patientDentalImagesDao.getPatientDentalImagesByPatientId(integers[0]);
        }
    }

    private static class InsertPatientDentalImagesAsyncTask extends AsyncTask<PatientDentalImages, Void, Long> {
        private PatientDentalImagesDao patientDentalImagesDao;

        private InsertPatientDentalImagesAsyncTask(PatientDentalImagesDao patientDentalImagesDao) {
            this.patientDentalImagesDao = patientDentalImagesDao;
        }

        @Override
        protected Long doInBackground(PatientDentalImages... patientDentalImages) {
//            patientDentalImagesDao.insert(patientDentalImages[0]);
            return patientDentalImagesDao.insert(patientDentalImages[0]);
        }
    }

    public void deleteByPatientId(int patientId) {
        new DeleteByPatientIdAsyncTask(patientDentalImagesDao).execute(patientId);
    }

    private static class DeleteByPatientIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        private PatientDentalImagesDao patientDentalImagesDao;

        private DeleteByPatientIdAsyncTask(PatientDentalImagesDao patientDentalImagesDao) {
            this.patientDentalImagesDao = patientDentalImagesDao;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            patientDentalImagesDao.deleteByPatientId(integers[0]);
            return null;
        }
    }
}
