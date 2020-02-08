package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DentistCheckUpViewModel extends AndroidViewModel {

    //Repository
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;
    private FirebaseAuth firebaseAuth;

    //patient attributes
    public MutableLiveData<String> patientName;
    public MutableLiveData<String> patientId;
    public MutableLiveData<String> age;
    public MutableLiveData<String> sex;
    public MutableLiveData<String> barangay;
    public MutableLiveData<String> purok;
    public MutableLiveData<String> pregnant;
    public MutableLiveData<String> allergies;
    public MutableLiveData<String> comorbiditiesAttribute;
    public MutableLiveData<String> urlUpperOcclusal;
    public MutableLiveData<String> urlLowerOcclusal;
    public MutableLiveData<String> urlLeftBuccal;
    public MutableLiveData<String> urlRightBuccal;
    public MutableLiveData<String> urlFront;
    public MutableLiveData<String> urlFrontFace;

    private MutableLiveData<Patient> patientMutableLiveData;
    private MutableLiveData<ArrayList<Comorbidity>> comorbidityMutableLiveData;
    private MutableLiveData<PatientDentalImages> patientDentalImagesMutableLiveData;

    public MutableLiveData<String> urlExpandedImage;
    public MutableLiveData<Boolean> isExpandedImageVisible;

    public DentistCheckUpViewModel(@NonNull Application application) {
        super(application);
        firebaseAuth = FirebaseAuth.getInstance();
        patientRepository = new PatientRepository(application);
        comorbidityRepository = new ComorbidityRepository(application);
        patientDentalImagesRepository = new PatientDentalImagesRepository(application);

        //patient data
        patientName = new MutableLiveData<>();
        patientId = new MutableLiveData<>();
        age = new MutableLiveData<>();
        sex = new MutableLiveData<>();
        barangay = new MutableLiveData<>();
        purok = new MutableLiveData<>();
        pregnant = new MutableLiveData<>();
        allergies = new MutableLiveData<>();
        //comorbidity data
        comorbiditiesAttribute = new MutableLiveData<>();
        //dental images data
        urlUpperOcclusal = new MutableLiveData<>();
        urlLowerOcclusal = new MutableLiveData<>();
        urlLeftBuccal = new MutableLiveData<>();
        urlRightBuccal = new MutableLiveData<>();
        urlFront = new MutableLiveData<>();
        urlFrontFace = new MutableLiveData<>();

        patientMutableLiveData = new MutableLiveData<>();
        comorbidityMutableLiveData = new MutableLiveData<>();
        patientDentalImagesMutableLiveData = new MutableLiveData<>();

        urlExpandedImage = new MutableLiveData<>();
        isExpandedImageVisible = new MutableLiveData<>();
    }

    public MutableLiveData<Patient> getPatientMutableLiveData() {
        return patientMutableLiveData;
    }

    public MutableLiveData<ArrayList<Comorbidity>> getComorbidityMutableLiveData() {
        return comorbidityMutableLiveData;
    }

    public MutableLiveData<PatientDentalImages> getPatientDentalImagesMutableLiveData() {
        return patientDentalImagesMutableLiveData;
    }

    public void getPatientData(String patientId) {
        int intPatientId = Integer.parseInt(patientId);
        patientRepository.getPatientByPatientId(intPatientId, patientMutableLiveData);
        comorbidityRepository.getComorbiditiesByPatientId(intPatientId, comorbidityMutableLiveData);
        patientDentalImagesRepository.getPatientDentalImagesByPatientId(intPatientId, patientDentalImagesMutableLiveData);
    }

    public void populatePatientDataToView(Patient patient) {
        patientName.setValue(patient.getPatientName());
        patientId.setValue(Integer.toString(patient.getPatientId()));
        age.setValue(patient.getAge());
        sex.setValue(patient.getSex());
        barangay.setValue(patient.getBarangay());
        purok.setValue(patient.getPurok());
        allergies.setValue(patient.getAllergies());

        if(patient.getPregnant()) {
            pregnant.setValue("Yes");
        } else {
            pregnant.setValue("No");
        }
    }

    public void populateComorbidityDataToView(ArrayList<Comorbidity> comorbidities) {
        ArrayList<String> comorbidityNameList = new ArrayList<>();
        for(Comorbidity comorbidity : comorbidities) {
            comorbidityNameList.add(comorbidity.getComorbidityName());
        }

        comorbiditiesAttribute.setValue(TextUtils.join(",", comorbidityNameList));
    }

    public void populateDentalImagesToView(PatientDentalImages patientDentalImages) {
        urlUpperOcclusal.setValue(patientDentalImages.getUrlUpperOcclusal());
        urlLowerOcclusal.setValue(patientDentalImages.getUrlLowerOcclusal());
        urlLeftBuccal.setValue(patientDentalImages.getUrlLeftBuccal());
        urlRightBuccal.setValue(patientDentalImages.getUrlRightBuccal());
        urlFront.setValue(patientDentalImages.getUrlFront());
        urlFrontFace.setValue(patientDentalImages.getUrlFrontFace());
    }

    public void expandImage(MutableLiveData<String> urlImage) {
        urlExpandedImage.setValue(urlImage.getValue());
    }
}
