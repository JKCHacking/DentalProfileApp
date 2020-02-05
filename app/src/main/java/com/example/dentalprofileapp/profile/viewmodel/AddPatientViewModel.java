package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.example.dentalprofileapp.utils.SingleLiveEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class AddPatientViewModel extends AndroidViewModel {
    //Component attributes
    private MutableLiveData<String> patientId;
    private MutableLiveData<String> registeredDate;
    private MutableLiveData<String> patientName;
    private MutableLiveData<String> age;
    private MutableLiveData<String> occupation;
    private MutableLiveData<String> allergies;

    private MutableLiveData<String> urlUpperOcclusal;
    private MutableLiveData<String> urlLeftBuccal;
    private MutableLiveData<String> urlFront;
    private MutableLiveData<String> urlRightBuccal;
    private MutableLiveData<String> urlLowerOcclusal;
    private MutableLiveData<String> urlFrontFace;
    private MutableLiveData<String> barangay;
    private MutableLiveData<String> purok;

    private String sex;
    private Boolean pregnant;

    //Observables
    public final ObservableBoolean isMale;
    public final ObservableBoolean isFemale;
    public final ObservableBoolean isPregnantYes;
    public final ObservableBoolean isPregnantNo;

    public final ObservableBoolean isNone;
    public final ObservableBoolean isBleedingDisorder;
    public final ObservableBoolean isCancer;
    public final ObservableBoolean isDiabetes;
    public final ObservableBoolean isHypertension;
    public final ObservableBoolean isKidneyDisease;
    public final ObservableBoolean isLiverDisease;
    public final ObservableBoolean isStroke;
    public final ObservableBoolean isThyroidDisease;
    public final ObservableBoolean isOther;

    //helper variables
    private LiveData<Patient> patientHighestId;
    private ArrayList<Comorbidity> comorbidityObjectList = new ArrayList<>();
    private ArrayList<Comorbidity> uncheckedComorbidityList = new ArrayList<>();
    private Set<String> comorbidityNameSet = new HashSet<>();
    private Set<String> uncheckedComorbidityNameSet = new HashSet<>();
    private SingleLiveEvent<Integer> openGalleryLiveData;
    private Boolean isUpdate;
    private MutableLiveData<Patient> patientResultLiveData;
    private MutableLiveData<ArrayList<Comorbidity>> comorbidityResultLiveData;
    private MutableLiveData<PatientDentalImages> patientDentalImagesResultLiveData;

    //entities
    private Patient mPatient;
    private PatientDentalImages patientDentalImages;

    //Repository references
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;
    private Patient patientRepoResult;
    private ArrayList<Comorbidity> comorbidityListRepoResult;
    private PatientDentalImages patientDentalImagesRepoResult;

    private int patientIdInt;

    public AddPatientViewModel(@NonNull Application application) {
        super(application);
        System.out.println("AddPatientViewModel constructor is called!");

        patientRepository = new PatientRepository(application);

        patientHighestId = patientRepository.getPatientWithHighestId();

        comorbidityRepository = new ComorbidityRepository(application);
        patientDentalImagesRepository = new PatientDentalImagesRepository(application);

        isMale = new ObservableBoolean();
        isFemale = new ObservableBoolean();
        isPregnantYes = new ObservableBoolean();
        isPregnantNo = new ObservableBoolean();

        isNone = new ObservableBoolean();
        isBleedingDisorder = new ObservableBoolean();
        isCancer= new ObservableBoolean();
        isDiabetes= new ObservableBoolean();
        isHypertension= new ObservableBoolean();
        isKidneyDisease= new ObservableBoolean();
        isLiverDisease= new ObservableBoolean();
        isStroke= new ObservableBoolean();
        isThyroidDisease= new ObservableBoolean();
        isOther= new ObservableBoolean();

        patientId = new MutableLiveData<>();
        registeredDate = new MutableLiveData<>();
        patientName = new MutableLiveData<>();
        age = new MutableLiveData<>();
        occupation = new MutableLiveData<>();
        allergies = new MutableLiveData<>();
        barangay = new MutableLiveData<>();
        purok = new MutableLiveData<>();

        urlUpperOcclusal = new MutableLiveData<>();
        urlLeftBuccal = new MutableLiveData<>();
        urlFront = new MutableLiveData<>();
        urlRightBuccal = new MutableLiveData<>();
        urlLowerOcclusal = new MutableLiveData<>();
        urlFrontFace = new MutableLiveData<>();

        openGalleryLiveData = new SingleLiveEvent<>();

        //get date today
        registeredDate.setValue(new SimpleDateFormat("MM/dd/YYYY", Locale.getDefault()).format(new Date()));
        isUpdate = false;

        patientResultLiveData = new MutableLiveData<>();
        comorbidityResultLiveData = new MutableLiveData<>();
        patientDentalImagesResultLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getPatientId() {
        return patientId;
    }

    public void setPatientId(MutableLiveData<String> patientId) {
        this.patientId = patientId;
    }

    public MutableLiveData<String> getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(MutableLiveData<String> registeredDate) {
        this.registeredDate = registeredDate;
    }

    public MutableLiveData<String> getPatientName() {
        return patientName;
    }

    public void setPatientName(MutableLiveData<String> patientName) {
        this.patientName = patientName;
    }

    public MutableLiveData<String> getAge() {
        return age;
    }

    public void setAge(MutableLiveData<String> age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MutableLiveData<String> getOccupation() {
        return occupation;
    }

    public void setOccupation(MutableLiveData<String> occupation) {
        this.occupation = occupation;
    }

    public MutableLiveData<String> getBarangay() {
        return barangay;
    }

    public void setBarangay(MutableLiveData<String> barangay) {
        this.barangay = barangay;
    }

    public MutableLiveData<String> getPurok() {
        return purok;
    }

    public void setPurok(MutableLiveData<String> purok) {
        this.purok = purok;
    }

    public MutableLiveData<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(MutableLiveData<String> allergies) {
        this.allergies = allergies;
    }

    public Boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    public LiveData<Patient> getPatientHighestId() {
        return patientHighestId;
    }

    public void setPatientHighestId(MutableLiveData<Patient> patientHighestId) {
        this.patientHighestId = patientHighestId;
    }

    public SingleLiveEvent<Integer> getOpenGalleryLiveData() {
        return openGalleryLiveData;
    }

    public void setOpenGalleryLiveData(SingleLiveEvent<Integer> openGalleryLiveData) {
        this.openGalleryLiveData = openGalleryLiveData;
    }

    public MutableLiveData<String> getUrlUpperOcclusal() {
        return urlUpperOcclusal;
    }

    public void setUrlUpperOcclusal(MutableLiveData<String> urlUpperOcclusal) {
        this.urlUpperOcclusal = urlUpperOcclusal;
    }

    public MutableLiveData<String> getUrlLeftBuccal() {
        return urlLeftBuccal;
    }

    public void setUrlLeftBuccal(MutableLiveData<String> urlLeftBuccal) {
        this.urlLeftBuccal = urlLeftBuccal;
    }

    public MutableLiveData<String> getUrlFront() {
        return urlFront;
    }

    public void setUrlFront(MutableLiveData<String> urlFront) {
        this.urlFront = urlFront;
    }

    public MutableLiveData<String> getUrlRightBuccal() {
        return urlRightBuccal;
    }

    public void setUrlRightBuccal(MutableLiveData<String> urlRightBuccal) {
        this.urlRightBuccal = urlRightBuccal;
    }

    public MutableLiveData<String> getUrlLowerOcclusal() {
        return urlLowerOcclusal;
    }

    public void setUrlLowerOcclusal(MutableLiveData<String> urlLowerOcclusal) {
        this.urlLowerOcclusal = urlLowerOcclusal;
    }

    public MutableLiveData<String> getUrlFrontFace() {
        return urlFrontFace;
    }

    public void setUrlFrontFace(MutableLiveData<String> urlFrontFace) {
        this.urlFrontFace = urlFrontFace;
    }

    public Boolean getUpdate() {
        return isUpdate;
    }

    public void setUpdate(Boolean update) {
        isUpdate = update;
    }

    public MutableLiveData<Patient> getPatientResultLiveData() {
        return patientResultLiveData;
    }

    public MutableLiveData<ArrayList<Comorbidity>> getComorbidityResultLiveData() {
        return comorbidityResultLiveData;
    }

    public MutableLiveData<PatientDentalImages> getPatientDentalImagesResultLiveData() {
        return patientDentalImagesResultLiveData;
    }

    public Patient getPatientRepoResult() {
        return patientRepoResult;
    }

    public void setPatientRepoResult(Patient patientRepoResult) {
        this.patientRepoResult = patientRepoResult;
    }

    public ArrayList<Comorbidity> getComorbidityListRepoResult() {
        return comorbidityListRepoResult;
    }

    public void setComorbidityListRepoResult(ArrayList<Comorbidity> comorbidityListRepoResult) {
        this.comorbidityListRepoResult = comorbidityListRepoResult;
    }

    public PatientDentalImages getPatientDentalImagesRepoResult() {
        return patientDentalImagesRepoResult;
    }

    public void setPatientDentalImagesRepoResult(PatientDentalImages patientDentalImagesRepoResult) {
        this.patientDentalImagesRepoResult = patientDentalImagesRepoResult;
    }

    public void insert(View view) {
        System.out.println("Insert method called!");

        composeEntities();

        if (isUpdate) {
            // do update logic here
            patientRepository.update(mPatient);
            patientDentalImagesRepository.update(patientDentalImages);
            //either add new checked or delete unchecked box. there is no update.
            comorbidityRepository.insert(comorbidityObjectList);

            if(!uncheckedComorbidityList.isEmpty()) {
                // delete all the unchecked comorbidities.
                int deletedComorbidity = comorbidityRepository
                        .deleteByPatientIdComorbidityName(uncheckedComorbidityList);
                System.out.println(deletedComorbidity);
                uncheckedComorbidityList.clear();
            }
        } else {
            patientRepository.insert(mPatient);
            comorbidityRepository.insert(comorbidityObjectList);
            patientDentalImagesRepository.insert(patientDentalImages);
        }

        ((Activity)(view.getContext())).finish();
    }

    private void composeEntities() {
        patientIdInt = Integer.parseInt(patientId.getValue());

        if (isMale.get()) {
            sex = "Male";
        } else if (isFemale.get()) {
            sex = "Female";
        }

        pregnant = isPregnantYes.get();

        //creating patient entity
        mPatient = new Patient();
        mPatient.setPatientId(patientIdInt);
        mPatient.setProfilePicture(R.drawable.ic_launcher_foreground);
        mPatient.setDate(registeredDate.getValue());
        mPatient.setPatientName(patientName.getValue());
        mPatient.setAge(age.getValue());
        mPatient.setSex(sex);
        mPatient.setOccupation(occupation.getValue());
        mPatient.setBarangay(barangay.getValue());
        mPatient.setPurok(purok.getValue());
        mPatient.setAllergies(allergies.getValue());
        mPatient.setPregnant(pregnant);

        for(String comorbidityName : comorbidityNameSet) {
            Comorbidity comorbidity = new Comorbidity();
            comorbidity.setFkPatientId(patientIdInt);
            comorbidity.setComorbidityName(comorbidityName);

            comorbidityObjectList.add(comorbidity);
        }

        for(String uncheckedComorbidityName : uncheckedComorbidityNameSet) {
            Comorbidity comorbidity = new Comorbidity();
            comorbidity.setFkPatientId(patientIdInt);
            comorbidity.setComorbidityName(uncheckedComorbidityName);

            uncheckedComorbidityList.add(comorbidity);
        }

        comorbidityNameSet.clear();
        uncheckedComorbidityNameSet.clear();

        //create patientDentalImages entity
        patientDentalImages = new PatientDentalImages();
        patientDentalImages.setFkPatientId(patientIdInt);
        patientDentalImages.setUrlUpperOcclusal(urlUpperOcclusal.getValue());
        patientDentalImages.setUrlFrontFace(urlFrontFace.getValue());
        patientDentalImages.setUrlLowerOcclusal(urlLowerOcclusal.getValue());
        patientDentalImages.setUrlFront(urlFront.getValue());
        patientDentalImages.setUrlRightBuccal(urlRightBuccal.getValue());
        patientDentalImages.setUrlLeftBuccal(urlLeftBuccal.getValue());
    }

    public void onClickCheckBox(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String comorbidityName = ((CheckBox) view).getText().toString();

        //if checkbox is checked
        if (checked) {
            // get the text from the checkbox and put in the arraylist.
            comorbidityNameSet.add(comorbidityName);
            uncheckedComorbidityNameSet.remove(comorbidityName);

        } else { //if checkbox is not checked
//            // create a set to hold the uncheckedComorbidity
            comorbidityNameSet.remove(comorbidityName);
            uncheckedComorbidityNameSet.add(comorbidityName);
        }
    }

    public void onClickChooseImage(View view) {
        // everytime imageview is pressed we need to modify the SingleLiveEvent data
        // this will trigger an observe callback in activity and calls the intent for gallery.
        int imageViewId = view.getId();

        switch(imageViewId) {
            case R.id.image_view_upper_occlusal:
                openGalleryLiveData.setValue(1);
                break;

            case R.id.image_view_left_buccal:
                openGalleryLiveData.setValue(2);
                break;

            case R.id.image_view_front:
                openGalleryLiveData.setValue(3);
                break;

            case R.id.image_view_right_buccal:
                openGalleryLiveData.setValue(4);
                break;

            case R.id.image_view_lower_occlusal:
                openGalleryLiveData.setValue(5);
                break;

            case R.id.image_view_front_face:
                openGalleryLiveData.setValue(6);
                break;
        }
    }
    public void getPatientDataFromRepo(String patientId) {
        int patientIdInt = Integer.parseInt(patientId);
        patientRepoResult = patientRepository.getPatientByPatientId(patientIdInt, patientResultLiveData);
        comorbidityListRepoResult = comorbidityRepository
                .getComorbiditiesByPatientId(patientIdInt, comorbidityResultLiveData);
        patientDentalImagesRepoResult = patientDentalImagesRepository
                .getPatientDentalImagesByPatientId(patientIdInt, patientDentalImagesResultLiveData);

        //this will only be called during offline scenario.
        if (patientRepoResult != null && comorbidityListRepoResult != null &&
                patientDentalImagesRepoResult != null) {
            populateDataToViews();
        }
    }
    public void populatePatientDataToViews() {
        registeredDate.setValue(patientRepoResult.getDate());
        patientName.setValue(patientRepoResult.getPatientName());
        age.setValue(patientRepoResult.getAge());
        occupation.setValue(patientRepoResult.getOccupation());
        allergies.setValue(patientRepoResult.getAllergies());

        if (patientRepoResult.getSex().equals("Male")) {
            isMale.set(true);
            isFemale.set(false);
        } else if(patientRepoResult.getSex().equals("Female")) {
            isMale.set(false);
            isFemale.set(true);
        }

        if (patientRepoResult.getPregnant()) {
            isPregnantYes.set(true);
            isPregnantNo.set(false);
        } else {
            isPregnantYes.set(false);
            isPregnantNo.set(true);
        }

        barangay.setValue(patientRepoResult.getBarangay());
        purok.setValue(patientRepoResult.getPurok());
    }

    public void populateComorbidityDataToViews() {
        this.comorbidityObjectList.clear();
        this.comorbidityObjectList = comorbidityListRepoResult;

        for(Comorbidity comorbidityObject : this.comorbidityObjectList) {
            switch(comorbidityObject.getComorbidityName()) {
                case "None":
                    isNone.set(true);
                    break;
                case "Bleeding Disorder":
                    isBleedingDisorder.set(true);
                    break;
                case "Cancer":
                    isCancer.set(true);
                    break;
                case "Diabetes":
                    isDiabetes.set(true);
                    break;
                case "Hypertension":
                    isHypertension.set(true);
                    break;
                case "Kidney Disease":
                    isKidneyDisease.set(true);
                    break;
                case "Liver Disease":
                    isLiverDisease.set(true);
                    break;
                case "Stroke":
                    isStroke.set(true);
                    break;
                case "Thyroid Disease":
                    isThyroidDisease.set(true);
                    break;
                case "Others":
                    isOther.set(true);
                    break;
            }
        }
    }

    public void populateDentalImagesToViews() {
        urlUpperOcclusal.setValue(patientDentalImagesRepoResult.getUrlUpperOcclusal());
        urlLeftBuccal.setValue(patientDentalImagesRepoResult.getUrlLeftBuccal());
        urlFront.setValue(patientDentalImagesRepoResult.getUrlFront());
        urlRightBuccal.setValue(patientDentalImagesRepoResult.getUrlRightBuccal());
        urlLowerOcclusal.setValue(patientDentalImagesRepoResult.getUrlLowerOcclusal());
        urlFrontFace.setValue(patientDentalImagesRepoResult.getUrlFrontFace());
    }

    public void populateDataToViews() {
        populatePatientDataToViews();
        populateComorbidityDataToViews();
        populateDentalImagesToViews();
    }
}
