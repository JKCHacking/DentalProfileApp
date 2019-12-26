package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.dentalprofileapp.profile.entities.Patient;

import java.util.List;

public class PatientRepository {
    private PatientDao patientDao;
    private LiveData<List<Patient>> allPatient;
    private LiveData<Patient> mPatientWithHighestId;

    public PatientRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDao = database.patientDao();
        allPatient = patientDao.getAllPatients();
        mPatientWithHighestId = patientDao.getPatientWithHighestId();
    }

    public void insert(Patient patient) {
        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public void update(Patient patient) {
        new UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    public void delete(Patient patient) {
        new DeletePatientAsyncTask(patientDao).execute(patient);
    }

    public void deleteByPatientId(int patientId) {
        new DeleteByPatientIdAsyncTask(patientDao).execute(patientId);
    }

    public void deleteAllPatients() {
        new DeleteAllPatientsAsyncTask(patientDao).execute();
    }

    public LiveData<List<Patient>> getAllPatients() {
        return allPatient;
    }

    public LiveData<Patient> getPatientWithHighestId() {
        return mPatientWithHighestId;
    }

    public Patient getPatientByPatientId(int patientId) {
        return patientDao.getPatientByPatientId(patientId);
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.insert(patients[0]);
            return null;
        }
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.update(patients[0]);
            return null;
        }
    }

    private static class DeletePatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private DeletePatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.delete(patients[0]);
            return null;
        }
    }

    private static class DeleteByPatientIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        private PatientDao patientDao;

        private DeleteByPatientIdAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            patientDao.deleteByPatientId(integers[0]);
            return null;
        }
    }

    private static class DeleteAllPatientsAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;

        private DeleteAllPatientsAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.deleteAllPatients();
            return null;
        }
    }
}
