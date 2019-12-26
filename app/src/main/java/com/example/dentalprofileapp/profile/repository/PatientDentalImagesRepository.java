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

    public PatientDentalImages getPatientByPatientId(int patientId) {
        return patientDentalImagesDao.getPatientByPatientId(patientId);
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
