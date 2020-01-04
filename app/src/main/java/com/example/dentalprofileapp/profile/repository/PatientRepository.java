package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.dentalprofileapp.profile.entities.Patient;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PatientRepository {
    private PatientDao patientDao;
    private LiveData<List<Patient>> allPatient;
    private List<Patient> allPatientList;
    private LiveData<Patient> mPatientWithHighestId;

    public PatientRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDao = database.patientDao();
    }

    public List<Patient> getAllPatientsOrderPatientName(){
        try {
            allPatientList = new GetAllPatientOrderPatientNameAsyncTask(patientDao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return allPatientList;
    }

    public List<Patient> getAllPatientsOrderPatientId(){
        try {
            allPatientList = new GetAllPatientOrderPatientIdAsyncTask(patientDao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return allPatientList;
    }

    public List<Patient> getAllPatientsOrderBarangay(){
        try {
            allPatientList = new GetAllPatientOrderBarangayAsyncTask(patientDao).execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return allPatientList;
    }

    public void insert(Patient patient) {
        try {
            long returnedId = new InsertPatientAsyncTask(patientDao).execute(patient).get();
            System.out.println(returnedId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
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
        mPatientWithHighestId = patientDao.getPatientWithHighestId();
        return mPatientWithHighestId;
    }

    public Patient getPatientByPatientId(int patientId){
        Patient patientResult = null;
        try {
            patientResult = new GetPatientByPatientIdAsyncTask(patientDao).execute(patientId).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return patientResult;
    }

    private static class GetPatientByPatientIdAsyncTask extends AsyncTask<Integer, Void, Patient> {
        private PatientDao patientDao;

        private GetPatientByPatientIdAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Patient doInBackground(Integer... integers) {
            return patientDao.getPatientByPatientId(integers[0]);
        }
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Long> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Long doInBackground(Patient... patients) {
            return patientDao.insert(patients[0]);
        }
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            int patientId = patients[0].getPatientId();
            int profilePicture = patients[0].getProfilePicture();
            String date = patients[0].getDate();
            String patientName = patients[0].getPatientName();
            String age = patients[0].getAge();
            String sex = patients[0].getSex();
            String occupation = patients[0].getOccupation();
            String barangay = patients[0].getBarangay();
            String purok = patients[0].getPurok();
            String allergies = patients[0].getAllergies();
            Boolean pregnant = patients[0].getPregnant();

            patientDao.update(patientId,
                    profilePicture,
                    date,
                    patientName,
                    age,
                    sex,
                    occupation,
                    barangay,
                    purok,
                    allergies,
                    pregnant);

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

    private static class GetAllPatientOrderPatientNameAsyncTask extends AsyncTask<Void, Void, List<Patient>> {
        private PatientDao patientDao;
        private GetAllPatientOrderPatientNameAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected List<Patient> doInBackground(Void... voids) {
            return patientDao.getAllPatientsOrderPatientName();
        }
    }

    private static class GetAllPatientOrderPatientIdAsyncTask extends AsyncTask<Void, Void, List<Patient>> {
        private PatientDao patientDao;
        private GetAllPatientOrderPatientIdAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected List<Patient> doInBackground(Void... voids) {
            return patientDao.getAllPatientsOrderPatientId();
        }
    }

    private static class GetAllPatientOrderBarangayAsyncTask extends AsyncTask<Void, Void, List<Patient>> {
        private PatientDao patientDao;
        private GetAllPatientOrderBarangayAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected List<Patient> doInBackground(Void... voids) {
            return patientDao.getAllPatientsOrderBarangay();
        }
    }
}
