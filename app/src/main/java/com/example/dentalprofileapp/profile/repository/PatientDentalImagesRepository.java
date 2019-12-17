package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;

public class PatientDentalImagesRepository {

    private PatientDentalImagesDao patientDentalImagesDao;

    public PatientDentalImagesRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDentalImagesDao = database.patientDentalImagesDao();
    }

    public void insert(PatientDentalImages patientDentalImages) {
        new PatientDentalImagesRepository.InsertPatientDentalImagesAsyncTask(patientDentalImagesDao).execute(patientDentalImages);
    }

    private static class InsertPatientDentalImagesAsyncTask extends AsyncTask<PatientDentalImages, Void, Void> {
        private PatientDentalImagesDao patientDentalImagesDao;

        private InsertPatientDentalImagesAsyncTask(PatientDentalImagesDao patientDentalImagesDao) {
            this.patientDentalImagesDao = patientDentalImagesDao;
        }

        @Override
        protected Void doInBackground(PatientDentalImages... patientDentalImages) {
            patientDentalImagesDao.insert(patientDentalImages[0]);
            return null;
        }
    }
}
