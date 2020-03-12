package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.auth.entity.User;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.example.dentalprofileapp.profile.entities.PatientOnlineModel;
import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.example.dentalprofileapp.profile.view.AddPatientActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientListViewModel extends AndroidViewModel {

    //Repository
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;
    private MutableLiveData<List<Patient>> allPatientsMutableData;
    private MutableLiveData<String> sortBy;
    private MutableLiveData<String> searchBy;
    public MutableLiveData<Boolean> hideOnlineMenus;
    private FirebaseAuth firebaseAuth;
    public MutableLiveData<User> userMutableData = new MutableLiveData<>();
    public MutableLiveData<List<Patient>> localPatients = new MutableLiveData<>();
    private ArrayList<String> checkedUploadPatientList = new ArrayList<>();
    public MutableLiveData<String> errorUpload = new MutableLiveData<>();

    public MutableLiveData<ArrayList<String>> urlListLiveData = new MutableLiveData<>();
    private ArrayList<String> urlList = new ArrayList<>();

    public PatientListViewModel(@NonNull Application application) {
        super(application);

        firebaseAuth = FirebaseAuth.getInstance();
        patientRepository = new PatientRepository(application, this);
        comorbidityRepository = new ComorbidityRepository(application);
        patientDentalImagesRepository = new PatientDentalImagesRepository(application);
        sortBy = new MutableLiveData<>();
        searchBy = new MutableLiveData<>();
        allPatientsMutableData = new MutableLiveData<>();
        hideOnlineMenus = new MutableLiveData<>();

        // if signed in user = true, hideOnlineMenus = false vice versa.
        hideOnlineMenus.setValue(!patientRepository.checkSignedInUser());

        sortBy.setValue("Patient Name");
        searchBy.setValue("Patient Name");
        allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderPatientName());
    }

    public void setAllPatients() {
        if (sortBy.getValue().equals("Patient Name")) {
            allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderPatientName());
        } else if (sortBy.getValue().equals("Patient ID")) {
            allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderPatientId());
        } else if (sortBy.getValue().equals("Barangay")) {
            allPatientsMutableData.postValue(patientRepository.getAllPatientsOrderBarangay());
        }
    }

    public MutableLiveData<String> getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(MutableLiveData<String> searchBy) {
        this.searchBy = searchBy;
    }

    public MutableLiveData<String> getSortBy() {
        return sortBy;
    }

    public MutableLiveData<List<Patient>> getAllPatientsMutableData() {
        return allPatientsMutableData;
    }

    public void setAllPatientsMutableData(MutableLiveData<List<Patient>> allPatientsMutableData) {
        this.allPatientsMutableData = allPatientsMutableData;
    }

    public void setSortBy(MutableLiveData<String> sortBy) {
        this.sortBy = sortBy;
    }

    public void insert(Patient patient) {
        patientRepository.insert(patient);
    }

    public void update(Patient patient) {
        patientRepository.update(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    public void deleteByPatientId(int patientId) {
        comorbidityRepository.deleteByPatientId(patientId);
        patientDentalImagesRepository.deleteByPatientId(patientId);
        patientRepository.deleteByPatientId(patientId);

        //this will update the record in recyclerview
        setAllPatients();
    }

    public void signOutUser(){
        patientRepository.signOutUser();
    }

    public void onClickAddPatient(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, AddPatientActivity.class);
        context.startActivity(intent);
    }

    public void getUser() {
        String userId = firebaseAuth.getCurrentUser().getUid();
        patientRepository.getUser(userId, userMutableData);
    }
    public void uploadDentalImages() {
        for(String patientId : checkedUploadPatientList) {
            int patientIdInt = Integer.parseInt(patientId);
            Patient patient = patientRepository.getLocalPatientByPatientId(patientIdInt);
            PatientDentalImages dentalImages = patientDentalImagesRepository
                    .getLocalPatientDentalImagesByPatientId(patientIdInt);

            String patientName = patient.getPatientName().replaceAll(" ", "_");
            uploadImages(dentalImages.getUrlFront(), patientName + "/front.jpeg");
            uploadImages(dentalImages.getUrlFrontFace(), patientName + "/front_face.jpeg");
            uploadImages(dentalImages.getUrlLeftBuccal(), patientName + "/left_buccal.jpeg");
            uploadImages(dentalImages.getUrlRightBuccal(), patientName + "/right_buccal.jpeg");
            uploadImages(dentalImages.getUrlUpperOcclusal(), patientName + "/upper_occlusal.jpeg");
            uploadImages(dentalImages.getUrlLowerOcclusal(), patientName + "/lower_occlusal.jpeg");
        }
    }

    private void uploadImages(String path, final String folderName) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference();

        Uri file = Uri.fromFile(new File(path));
        StorageReference patientImage = storageRef.child(folderName);
        UploadTask uploadTask = patientImage.putFile(file);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                System.out.println("Failed to upload image");
                errorUpload.setValue(exception.getMessage());
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(folderName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Got the download URL for 'users/me/profile.png'
                        String patientName = getPatientNameInUrl(uri.toString());
                        urlList.add(uri.toString());
                        ArrayList<String> singlePatientUrlList = getSinglePatientDentalImages(patientName);

                        if(singlePatientUrlList.size() == 6) {
                            composePatientOnlineModel(singlePatientUrlList);
                        }
                        // urlListLiveData.setValue(urlList);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        System.out.println("Failed to get Image URL");
                    }
                });
            }
        });
    }

    public ArrayList<String> getSinglePatientDentalImages(String patientName) {
        ArrayList<String> singlePatientUrlList = new ArrayList<>();
        for(String url : this.urlList) {
            if (url.contains(patientName.replaceAll(" ", "_")) && url.contains("front_face.jpeg")) {
                singlePatientUrlList.add(url);
            } else if (url.contains(patientName.replaceAll(" ", "_")) && url.contains("front.jpeg")) {
                singlePatientUrlList.add(url);
            } else if (url.contains(patientName.replaceAll(" ", "_")) && url.contains("left_buccal.jpeg")) {
                singlePatientUrlList.add(url);
            } else if (url.contains(patientName.replaceAll(" ", "_")) && url.contains("right_buccal.jpeg")) {
                singlePatientUrlList.add(url);
            } else if (url.contains(patientName.replaceAll(" ", "_")) && url.contains("lower_occlusal.jpeg")) {
                singlePatientUrlList.add(url);
            } else if (url.contains(patientName.replaceAll(" ", "_")) && url.contains("upper_occlusal.jpeg")) {
                singlePatientUrlList.add(url);
            }
        }

        return singlePatientUrlList;
    }

    public String getPatientNameInUrl(String url) {
		String[] strUrlArray = url.split("/");
		String imageInfo = strUrlArray[strUrlArray.length - 1];
		String[] imageInfoSplit = imageInfo.split("%");
		String patientName = imageInfoSplit[0];
        return patientName;
    }

    public void getLocalPatientData() {
        localPatients.setValue(patientRepository.getAllLocalPatients());
    }

    private void composePatientOnlineModel(ArrayList<String> urlList) {
        System.out.println(urlList);

        for(String patientId : checkedUploadPatientList) {
            int patientIdInt = Integer.parseInt(patientId);
            ArrayList<String> comorbidityNames = new ArrayList<>();
            HashMap<String, String> dentalImages = new HashMap<>();

            Patient patient = patientRepository.getLocalPatientByPatientId(patientIdInt);
            if (urlList.get(0).contains(patient.getPatientName())) {
                ArrayList<Comorbidity> comorbidities = comorbidityRepository.getLocalComorbiditiesByPatientId(patientIdInt);

                for(Comorbidity comorbidity : comorbidities ) {
                    comorbidityNames.add(comorbidity.getComorbidityName());
                }

                PatientOnlineModel patientOnlineModel = new PatientOnlineModel();
                patientOnlineModel.setAge(patient.getAge());
                patientOnlineModel.setAllergies(patient.getAllergies());
                patientOnlineModel.setBarangay(patient.getBarangay());
                patientOnlineModel.setOccupation(patient.getOccupation());
                patientOnlineModel.setPatientId(patient.getPatientId());
                patientOnlineModel.setPatientName(patient.getPatientName());
                patientOnlineModel.setPregnant(patient.getPregnant());
                patientOnlineModel.setPurok(patient.getPurok());
                patientOnlineModel.setRegisteredDate(patient.getDate());
                patientOnlineModel.setSex(patient.getSex());
                patientOnlineModel.setComorbidities(comorbidityNames);

                for(String url : urlList) {
                    if(url.contains(patient.getPatientName().replaceAll(" ", "_"))) {
                        if(url.contains("face")) {
                            dentalImages.put("frontFace", url);
                        } else if(url.contains("right")) {
                            dentalImages.put("rightBuccal", url);
                        } else if(url.contains("left")) {
                            dentalImages.put("leftBuccal", url);
                        }else if(url.contains("upper")) {
                            dentalImages.put("upperOcclusal", url);
                        }else if(url.contains("lower")) {
                            dentalImages.put("lowerOcclusal", url);
                        }else {
                            dentalImages.put("front", url);
                        }
                    }
                }
                patientOnlineModel.setDentalImages(dentalImages);
                patientRepository.uploadPatientOnlineModel(patientOnlineModel, allPatientsMutableData);
            }
            
        }
        checkedUploadPatientList.clear();
    }
    public void setCheckedUploadPatientList(String checkedPatientID) {
        checkedUploadPatientList.add(checkedPatientID);
    }
    public void deleteCheckedUploadPatientList(String patientId) {
        checkedUploadPatientList.remove(patientId);
    }
    public void composeAndUpload(ArrayList<String> urlList) {
        if (urlList.size() == (checkedUploadPatientList.size() * 6)) {
            composePatientOnlineModel(urlList);
        }
    }
}
