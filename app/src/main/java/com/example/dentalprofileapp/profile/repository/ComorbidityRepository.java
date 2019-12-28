package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.dentalprofileapp.profile.entities.Comorbidity;

import java.util.ArrayList;

public class ComorbidityRepository {
    private ComorbidityDao comorbidityDao;

    public ComorbidityRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        comorbidityDao = database.comorbidityDao();
    }

    public void insert(ArrayList<Comorbidity> comorbidityList) {
        new InsertComorbidityAsyncTask(comorbidityDao).execute(comorbidityList);
    }

    private static class InsertComorbidityAsyncTask extends AsyncTask<ArrayList<Comorbidity>, Void, Void> {
        private ComorbidityDao comorbidityDao;

        private InsertComorbidityAsyncTask(ComorbidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }

        @Override
        protected Void doInBackground(ArrayList<Comorbidity>... arrayLists) {
            for(ArrayList<Comorbidity> comorbidities : arrayLists) {
                comorbidityDao.insertBatch(comorbidities);
            }
            return null;
        }
    }

    public void deleteByPatientId(int patientId) {
        new DeleteByPatientIdAsyncTask(comorbidityDao).execute(patientId);
    }

    private static class DeleteByPatientIdAsyncTask extends AsyncTask<Integer, Void, Void> {
        private ComorbidityDao comorbidityDao;

        private DeleteByPatientIdAsyncTask(ComorbidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }
        @Override
        protected Void doInBackground(Integer... integers) {
            comorbidityDao.deleteByPatientId(integers[0]);
            return null;
        }
    }

    public Comorbidity getPatientByPatientId(int patientId) {
        return comorbidityDao.getPatientByPatientId(patientId);
    }
}