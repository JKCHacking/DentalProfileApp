package com.example.dentalprofileapp.profile.repository;

public class ComorbidityRepository {
    private ComorbidityDao comorbidityDao;

    public ComorbidityRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        comorbidityDao = database.comorbidityDao();
    }

    public void insert(ArrayList<Comorbidity> comorbidityList) {
        new InsertComorbidityAsyncTask(comorbidityDao).execute(comorbidityList);
    }

    private static class InsertComorbidityAsyncTask extends AsyncTask<Comobidity, Void, Void> {
        private ComobidityDao comorbidityDao;

        private InsertPatientAsyncTask(ComobidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }

        @Override
        protected Void doInBackground(Comobidity... Comobidities) {
            comorbidityDao.insertBatch(Comobidities);
            return null;
        }
    }
}