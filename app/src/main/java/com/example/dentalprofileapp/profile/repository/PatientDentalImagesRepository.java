package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.dentalprofileapp.profile.entities.PatientDentalImages;

import java.util.concurrent.ExecutionException;

public class PatientDentalImagesRepository {

    private PatientDentalImagesDao patientDentalImagesDao;

    public PatientDentalImagesRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDentalImagesDao = database.patientDentalImagesDao();
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

    public PatientDentalImages getPatientDentalImagesByPatientId(int patientId){
        PatientDentalImages patientDentalImagesResult = null;
        try {
            patientDentalImagesResult = new GetPatientDentalImagesByPatientIdAsyncTask(patientDentalImagesDao).execute(patientId).get();
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
