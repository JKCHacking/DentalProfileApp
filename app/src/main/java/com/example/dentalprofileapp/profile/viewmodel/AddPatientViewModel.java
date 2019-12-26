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
import java.util.Locale;

public class AddPatientViewModel extends AndroidViewModel {
    //Component attributes
    private MutableLiveData<String> patientId;
    private String registeredDate;
    private String patientName;
    private String age;
    private String sex;
    private String occupation;
    private String barangay;
    private String purok;
    private String allergies;
    private Boolean pregnant;
    private MutableLiveData<String> urlUpperOcclusal;
    private MutableLiveData<String> urlLeftBuccal;
    private MutableLiveData<String> urlFront;
    private MutableLiveData<String> urlRightBuccal;
    private MutableLiveData<String> urlLowerOcclusal;
    private MutableLiveData<String> urlFrontFace;

    //Observables
    public final ObservableBoolean isMale;
    public final ObservableBoolean isFemale;
    public final ObservableBoolean isPregnant;

    //helper variables
    private LiveData<Patient> patientHighestId;
    private ArrayList<String> comorbidityNameList = new ArrayList<>();
    private ArrayList<Comorbidity> comorbidityList = new ArrayList<>();
    private SingleLiveEvent<Integer> openGalleryLiveData;

    //entities
    private Patient mPatient;
    private PatientDentalImages patientDentalImages;

    //Repository references
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;
    private PatientDentalImagesRepository patientDentalImagesRepository;

    int patientIdInt;

    public AddPatientViewModel(@NonNull Application application) {
        super(application);

        patientRepository = new PatientRepository(application);
        comorbidityRepository = new ComorbidityRepository(application);
        patientDentalImagesRepository = new PatientDentalImagesRepository(application);

        isMale = new ObservableBoolean();
        isFemale = new ObservableBoolean();
        isPregnant = new ObservableBoolean();
        patientHighestId = patientRepository.getPatientWithHighestId();
        patientId = new MutableLiveData<>();

        urlUpperOcclusal = new MutableLiveData<>();
        urlLeftBuccal = new MutableLiveData<>();
        urlFront = new MutableLiveData<>();
        urlRightBuccal = new MutableLiveData<>();
        urlLowerOcclusal = new MutableLiveData<>();
        urlFrontFace = new MutableLiveData<>();

        openGalleryLiveData = new SingleLiveEvent<>();

        //get date today
        registeredDate = new SimpleDateFormat("MM/dd/YYYY", Locale.getDefault()).format(new Date());
    }

    public MutableLiveData<String> getPatientId() {
        return patientId;
    }

    public void setPatientId(MutableLiveData<String> patientId) {
        this.patientId = patientId;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getPurok() {
        return purok;
    }

    public void setPurok(String purok) {
        this.purok = purok;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
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

    public void insert(View view) {
        System.out.println("Insert method called!");

        composeEntities();

        patientRepository.insert(mPatient);
        comorbidityRepository.insert(comorbidityList);
        patientDentalImagesRepository.insert(patientDentalImages);

        ((Activity)(view.getContext())).finish();
    }

    private void composeEntities() {
        patientIdInt = Integer.parseInt(patientId.getValue());

        if (isMale.get()) {
            sex = "Male";
        } else if (isFemale.get()) {
            sex = "Female";
        }

        pregnant = isPregnant.get();

        mPatient = new Patient(R.drawable.ic_launcher_foreground,
                patientIdInt,
                registeredDate,
                patientName,
                age,
                sex,
                occupation,
                barangay,
                purok,
                allergies,
                pregnant);

        for(String comorbidityName: comorbidityNameList) {
            comorbidityList.add(new Comorbidity(patientIdInt, comorbidityName));
        }

        patientDentalImages = new PatientDentalImages(
                patientIdInt,
                urlUpperOcclusal.getValue(),
                urlLeftBuccal.getValue(),
                urlFront.getValue(),
                urlRightBuccal.getValue(),
                urlLowerOcclusal.getValue(),
                urlFrontFace.getValue()
        );
    }

    public void onClickCheckBox(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        //if checkbox is checked
        if (checked) {
            // get the text from the checkbox and put in the arraylist.
            comorbidityNameList.add(((CheckBox) view).getText().toString());
        } else { //if checkbox is not checked
            // remove the text from the arraylist.
            comorbidityNameList.remove(((CheckBox) view).getText().toString());
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

    public void getAllDataFromRepositoryByPatientId() {
        int patientId = Integer.parseInt(this.patientId.getValue());
        Patient patient = patientRepository.getPatientByPatientId(patientId);
        Comorbidity comorbidity = comorbidityRepository.getPatientByPatientId(patientId);
        PatientDentalImages patientDentalImages = patientDentalImagesRepository.getPatientByPatientId(patientId);
    }
}
