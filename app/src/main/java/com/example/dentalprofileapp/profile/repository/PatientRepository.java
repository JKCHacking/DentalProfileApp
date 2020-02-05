package com.example.dentalprofileapp.profile.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.auth.repository.AuthRepository;
import com.example.dentalprofileapp.profile.dao.PatientDao;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.viewmodel.PatientListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PatientRepository {
    private PatientDao patientDao;
    private List<Patient> allPatientList;
    private LiveData<Patient> mPatientWithHighestId;
    private AuthRepository authRepository;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore firebaseFirestore;
    private PatientListViewModel patientListViewModel;
    private Patient patientResult;

    public PatientRepository(Application application, PatientListViewModel patientListViewModel) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDao = database.patientDao();
        authRepository = new AuthRepository();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        this.patientListViewModel = patientListViewModel;

    }

    public PatientRepository(Application application) {
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDao = database.patientDao();
        authRepository = new AuthRepository();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    private void getPatientFromFirestore(String sortBy) {
        CollectionReference patientsRef = firebaseFirestore.collection("patients");
        patientsRef.orderBy(sortBy);
        patientsRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            allPatientList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId() + " => " + document.getData());
                                System.out.println("Patient Name: " + document.get("patientName"));

                                Patient patientObject = new Patient();
                                patientObject.setId(document.getLong("patientId").intValue());
                                patientObject.setProfilePicture(R.drawable.ic_launcher_foreground); //TODO Temporary value
                                patientObject.setPatientName(document.getString("patientName"));
                                patientObject.setBarangay(document.getString("barangay"));
                                patientObject.setPatientId(document.getLong("patientId").intValue());
                                patientObject.setDate(document.getString("registeredDate"));
                                patientObject.setAge(document.getString("age"));
                                patientObject.setSex(document.getString("sex"));
                                patientObject.setOccupation(document.getString("occupation"));
                                patientObject.setPurok(document.getString("purok"));
                                patientObject.setAllergies(document.getString("allergies"));
                                patientObject.setPregnant(document.getBoolean("pregnant"));

                                allPatientList.add(patientObject);
                            }
                            patientListViewModel.getAllPatientsMutableData().postValue(allPatientList);
                        } else {
                            System.out.println("Error getting documents." + task.getException());
                        }
                    }
                });
    }

    public List<Patient> getAllPatientsOrderPatientName(){
        try {
            if (authRepository.checkSignedInUser()){
                //do something to get the user from the firebase database.
                System.out.println("There is a signed in user!");
                getPatientFromFirestore("patientName");
            } else {
                 allPatientList = new GetAllPatientOrderPatientNameAsyncTask(patientDao).execute().get();
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return allPatientList;
    }

    public List<Patient> getAllPatientsOrderPatientId(){
        try {
            if (authRepository.checkSignedInUser()){
                //do something to get the user from the firebase database.
                System.out.println("There is a signed in user!");
                getPatientFromFirestore("patientId");
            } else {
                allPatientList = new GetAllPatientOrderPatientIdAsyncTask(patientDao).execute().get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return allPatientList;
    }

    public List<Patient> getAllPatientsOrderBarangay(){
        try {
            if (authRepository.checkSignedInUser()){
                //do something to get the user from the firebase database.
                System.out.println("There is a signed in user!");
                getPatientFromFirestore("barangay");
            } else {
                allPatientList = new GetAllPatientOrderBarangayAsyncTask(patientDao).execute().get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return allPatientList;
    }

    public LiveData<Patient> getPatientWithHighestId() {
        mPatientWithHighestId = patientDao.getPatientWithHighestId();
        return mPatientWithHighestId;
    }

    public Patient getPatientByPatientId(final int patientId,
                                         final MutableLiveData<Patient> patientResultLiveData){
        patientResult = null;
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
                                            patientResult = new Patient();
                                            patientResult.setId(document.getLong("patientId").intValue());
                                            patientResult.setProfilePicture(R.drawable.ic_launcher_foreground); //TODO Temporary value
                                            patientResult.setPatientName(document.getString("patientName"));
                                            patientResult.setBarangay(document.getString("barangay"));
                                            patientResult.setPatientId(document.getLong("patientId").intValue());
                                            patientResult.setDate(document.getString("registeredDate"));
                                            patientResult.setAge(document.getString("age"));
                                            patientResult.setSex(document.getString("sex"));
                                            patientResult.setOccupation(document.getString("occupation"));
                                            patientResult.setPurok(document.getString("purok"));
                                            patientResult.setAllergies(document.getString("allergies"));
                                            patientResult.setPregnant(document.getBoolean("pregnant"));
                                            break;
                                        }
                                    }

                                    if (patientResult == null) {
                                        System.out.println("Cannot find patient with Patient Id " + patientId);
                                    } else {
                                        patientResultLiveData.postValue(patientResult);
                                    }

                                } else {
                                    System.out.println("Error getting documents." + task.getException());
                                }
                            }
                        });
            } else {
                patientResult = new GetPatientByPatientIdAsyncTask(patientDao).execute(patientId).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(5000);
        } catch(InterruptedException ie) {
            ie.printStackTrace();
        }

        return patientResult;
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

    //====================================ASYNC TASK CLASSES====================================

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
