package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.auth.repository.AuthRepository;
import com.example.dentalprofileapp.profile.dao.ComorbidityDao;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ComorbidityRepository {
    private ComorbidityDao comorbidityDao;
    private ArrayList<Comorbidity> comorbiditiesResult;
    private AuthRepository authRepository;
    private FirebaseFirestore firebaseFirestore;

    public ComorbidityRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        comorbidityDao = database.comorbidityDao();
        firebaseFirestore = FirebaseFirestore.getInstance();
        authRepository = new AuthRepository();
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

    public ArrayList<Comorbidity> getComorbiditiesByPatientId(final int patientId,
                                                              final MutableLiveData<ArrayList<Comorbidity>> comorbidityListLiveData){
        comorbiditiesResult = null;
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
                                            comorbiditiesResult = new ArrayList<>();

                                            ArrayList<String> comorbiditiesFromDocument = (ArrayList<String>) document.get("comorbidities");
                                            for (String comorbidityName : comorbiditiesFromDocument) {
                                                Comorbidity comorbidityObject = new Comorbidity();
                                                comorbidityObject.setFkPatientId(patientId);
                                                comorbidityObject.setComorbidityName(comorbidityName);
                                                comorbiditiesResult.add(comorbidityObject);
                                            }
                                            break;
                                        }
                                    }

                                    if (comorbiditiesResult == null) {
                                        System.out.println("Cannot find patient with Patient Id " + patientId);
                                    } else {
                                        comorbidityListLiveData.setValue(comorbiditiesResult);
                                    }
                                } else {
                                    System.out.println("Error getting documents." + task.getException());
                                }
                            }
                        });
            } else {
                comorbiditiesResult = new GetPatientByPatientIdAsyncTask(comorbidityDao).execute(patientId).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return comorbiditiesResult;
    }

    public List<Comorbidity> getAllComorbidities() {
        List<Comorbidity> comorbidityList = null;
        try {
            comorbidityList = new GetAllComorbities(comorbidityDao).execute().get();
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return comorbidityList;
    }

    public ArrayList<Comorbidity> getLocalComorbiditiesByPatientId(int patientId) {
        try {
            return new GetPatientByPatientIdAsyncTask(comorbidityDao).execute(patientId).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class GetAllComorbities extends AsyncTask<Void, Void, List<Comorbidity>> {
        private ComorbidityDao comorbidityDao;

        private GetAllComorbities(ComorbidityDao comorbidityDao) {
            this.comorbidityDao = comorbidityDao;
        }

        @Override
        protected List<Comorbidity> doInBackground(Void... voids) {
            return comorbidityDao.getAllComorbidities();
        }
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