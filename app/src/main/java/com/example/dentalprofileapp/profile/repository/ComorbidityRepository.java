package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.room.Delete;

import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ComorbidityRepository {
    private ComorbidityDao comorbidityDao;

    public ComorbidityRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        comorbidityDao = database.comorbidityDao();
    }

    public void insert(ArrayList<Comorbidity> comorbidityList) {
        new InsertComorbidityAsyncTask(comorbidityDao).execute(comorbidityList);
    }

    public Integer deleteByPatientIdComorbidityName(ArrayList<Comorbidity> comorbidities){
        try {
            return new DeleteByPatientIdComorbidityNameAsyncTask(comorbidityDao).execute(comorbidities).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void update(ArrayList<Comorbidity> comorbidityArrayList) {
        new UpdateAsyncTask(comorbidityDao).execute(comorbidityArrayList);
    }

    private static class UpdateAsyncTask extends AsyncTask<ArrayList<Comorbidity>, Void, Void> {
        ComorbidityDao comorbidityDao;

        public UpdateAsyncTask(ComorbidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }

        @Override
        protected Void doInBackground(ArrayList<Comorbidity>... arrayLists) {
            for(ArrayList<Comorbidity> comorbidities : arrayLists) {
                comorbidityDao.updateBatch(comorbidities);
            }
            return null;
        }
    }

    private static class DeleteByPatientIdComorbidityNameAsyncTask extends AsyncTask<ArrayList<Comorbidity>, Void, Integer> {
        ComorbidityDao comorbidityDao;

        public DeleteByPatientIdComorbidityNameAsyncTask(ComorbidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }

        @Override
        protected Integer doInBackground(ArrayList<Comorbidity>... arrayLists) {
            int deletedComorbidityId = -1;
            for (Comorbidity comorbidity: arrayLists[0]) {
                deletedComorbidityId = comorbidityDao.deleteByPatientIdComorbidityName(comorbidity.getFkPatientId(), comorbidity.getComorbidityName());
                System.out.println(deletedComorbidityId);
            }
            return deletedComorbidityId;
        }
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

    public ArrayList<Comorbidity> getComorbiditiesByPatientId(int patientId){
        ArrayList<Comorbidity> comorbiditiesResult = null;
        try {
            comorbiditiesResult = new GetPatientByPatientIdAsyncTask(comorbidityDao).execute(patientId).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return comorbiditiesResult;
    }

    private static class GetPatientByPatientIdAsyncTask extends AsyncTask<Integer, Void, ArrayList<Comorbidity>> {
        private ComorbidityDao comorbidityDao;

        private GetPatientByPatientIdAsyncTask(ComorbidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }

        @Override
        protected ArrayList<Comorbidity> doInBackground(Integer... integers) {
            return (ArrayList)comorbidityDao.getComorbidityByPatientId(integers[0]);
        }
    }
}