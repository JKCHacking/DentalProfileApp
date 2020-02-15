package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dentalprofileapp.auth.entity.User;
import com.example.dentalprofileapp.auth.repository.AuthRepository;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;
import com.example.dentalprofileapp.profile.repository.ComorbidityRepository;
import com.example.dentalprofileapp.profile.repository.DentistFindingsRepository;
import com.example.dentalprofileapp.profile.repository.PatientDentalImagesRepository;
import com.example.dentalprofileapp.profile.repository.PatientRepository;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;

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
    public DentistFinding dentistFinding;
    public AuthRepository authRepository;
    public MutableLiveData<User> userLiveData;

    private MutableLiveData<String> tooth18 = new MutableLiveData<>();
    private MutableLiveData<String> tooth17 = new MutableLiveData<>();
    private MutableLiveData<String> tooth16 = new MutableLiveData<>();
    private MutableLiveData<String> tooth1555 = new MutableLiveData<>();
    private MutableLiveData<String> tooth1454 = new MutableLiveData<>();
    private MutableLiveData<String> tooth1353 = new MutableLiveData<>();
    private MutableLiveData<String> tooth1252 = new MutableLiveData<>();
    private MutableLiveData<String> tooth1151 = new MutableLiveData<>();
    private MutableLiveData<String> tooth2161 = new MutableLiveData<>();
    private MutableLiveData<String> tooth2262 = new MutableLiveData<>();
    private MutableLiveData<String> tooth2363 = new MutableLiveData<>();
    private MutableLiveData<String> tooth2464 = new MutableLiveData<>();
    private MutableLiveData<String> tooth2565 = new MutableLiveData<>();
    private MutableLiveData<String> tooth26 = new MutableLiveData<>();
    private MutableLiveData<String> tooth27 = new MutableLiveData<>();
    private MutableLiveData<String> tooth28 = new MutableLiveData<>();
    private MutableLiveData<String> tooth48 = new MutableLiveData<>();
    private MutableLiveData<String> tooth47 = new MutableLiveData<>();
    private MutableLiveData<String> tooth46 = new MutableLiveData<>();
    private MutableLiveData<String> tooth4585 = new MutableLiveData<>();
    private MutableLiveData<String> tooth4484 = new MutableLiveData<>();
    private MutableLiveData<String> tooth4383 = new MutableLiveData<>();
    private MutableLiveData<String> tooth4282 = new MutableLiveData<>();
    private MutableLiveData<String> tooth4181 = new MutableLiveData<>();
    private MutableLiveData<String> tooth3171 = new MutableLiveData<>();
    private MutableLiveData<String> tooth3272 = new MutableLiveData<>();
    private MutableLiveData<String> tooth3373 = new MutableLiveData<>();
    private MutableLiveData<String> tooth3474 = new MutableLiveData<>();
    private MutableLiveData<String> tooth3575 = new MutableLiveData<>();
    private MutableLiveData<String> tooth36 = new MutableLiveData<>();
    private MutableLiveData<String> tooth37 = new MutableLiveData<>();
    private MutableLiveData<String> tooth38 = new MutableLiveData<>();
    private String plaqueScore;
    private String urgencyOfTreatment;

    //plaqueScore
    public final ObservableBoolean plaqueScore0 = new ObservableBoolean();
    public final ObservableBoolean plaqueScore1 = new ObservableBoolean();
    public final ObservableBoolean plaqueScore2 = new ObservableBoolean();
    public final ObservableBoolean plaqueScore3 = new ObservableBoolean();

    //urgency of treatment
    public final ObservableBoolean urgency0 = new ObservableBoolean();
    public final ObservableBoolean urgency1 = new ObservableBoolean();
    public final ObservableBoolean urgency2 = new ObservableBoolean();
    public final ObservableBoolean urgency3 = new ObservableBoolean();
    public final ObservableBoolean urgency4 = new ObservableBoolean();

    public DentistFindingsRepository dentistFindingsRepository;
    public MutableLiveData<DentistFinding> dentistFindingLiveData;

    public DentistCheckUpViewModel(@NonNull Application application) {
        super(application);

        authRepository = new AuthRepository();
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
        dentistFinding = new DentistFinding();
        userLiveData = new MutableLiveData<>();

        dentistFindingsRepository = new DentistFindingsRepository();
        dentistFindingLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getTooth18() {
        return tooth18;
    }

    public void setTooth18(MutableLiveData<String> tooth18) {
        this.tooth18 = tooth18;
    }

    public MutableLiveData<String> getTooth17() {
        return tooth17;
    }

    public void setTooth17(MutableLiveData<String> tooth17) {
        this.tooth17 = tooth17;
    }

    public MutableLiveData<String> getTooth16() {
        return tooth16;
    }

    public void setTooth16(MutableLiveData<String> tooth16) {
        this.tooth16 = tooth16;
    }

    public MutableLiveData<String> getTooth1555() {
        return tooth1555;
    }

    public void setTooth1555(MutableLiveData<String> tooth1555) {
        this.tooth1555 = tooth1555;
    }

    public MutableLiveData<String> getTooth1454() {
        return tooth1454;
    }

    public void setTooth1454(MutableLiveData<String> tooth1454) {
        this.tooth1454 = tooth1454;
    }

    public MutableLiveData<String> getTooth1353() {
        return tooth1353;
    }

    public void setTooth1353(MutableLiveData<String> tooth1353) {
        this.tooth1353 = tooth1353;
    }

    public MutableLiveData<String> getTooth1252() {
        return tooth1252;
    }

    public void setTooth1252(MutableLiveData<String> tooth1252) {
        this.tooth1252 = tooth1252;
    }

    public MutableLiveData<String> getTooth1151() {
        return tooth1151;
    }

    public void setTooth1151(MutableLiveData<String> tooth1151) {
        this.tooth1151 = tooth1151;
    }

    public MutableLiveData<String> getTooth2161() {
        return tooth2161;
    }

    public void setTooth2161(MutableLiveData<String> tooth2161) {
        this.tooth2161 = tooth2161;
    }

    public MutableLiveData<String> getTooth2262() {
        return tooth2262;
    }

    public void setTooth2262(MutableLiveData<String> tooth2262) {
        this.tooth2262 = tooth2262;
    }

    public MutableLiveData<String> getTooth2363() {
        return tooth2363;
    }

    public void setTooth2363(MutableLiveData<String> tooth2363) {
        this.tooth2363 = tooth2363;
    }

    public MutableLiveData<String> getTooth2464() {
        return tooth2464;
    }

    public void setTooth2464(MutableLiveData<String> tooth2464) {
        this.tooth2464 = tooth2464;
    }

    public MutableLiveData<String> getTooth2565() {
        return tooth2565;
    }

    public void setTooth2565(MutableLiveData<String> tooth2565) {
        this.tooth2565 = tooth2565;
    }

    public MutableLiveData<String> getTooth26() {
        return tooth26;
    }

    public void setTooth26(MutableLiveData<String> tooth26) {
        this.tooth26 = tooth26;
    }

    public MutableLiveData<String> getTooth27() {
        return tooth27;
    }

    public void setTooth27(MutableLiveData<String> tooth27) {
        this.tooth27 = tooth27;
    }

    public MutableLiveData<String> getTooth28() {
        return tooth28;
    }

    public void setTooth28(MutableLiveData<String> tooth28) {
        this.tooth28 = tooth28;
    }

    public MutableLiveData<String> getTooth48() {
        return tooth48;
    }

    public void setTooth48(MutableLiveData<String> tooth48) {
        this.tooth48 = tooth48;
    }

    public MutableLiveData<String> getTooth47() {
        return tooth47;
    }

    public void setTooth47(MutableLiveData<String> tooth47) {
        this.tooth47 = tooth47;
    }

    public MutableLiveData<String> getTooth46() {
        return tooth46;
    }

    public void setTooth46(MutableLiveData<String> tooth46) {
        this.tooth46 = tooth46;
    }

    public MutableLiveData<String> getTooth4585() {
        return tooth4585;
    }

    public void setTooth4585(MutableLiveData<String> tooth4585) {
        this.tooth4585 = tooth4585;
    }

    public MutableLiveData<String> getTooth4484() {
        return tooth4484;
    }

    public void setTooth4484(MutableLiveData<String> tooth4484) {
        this.tooth4484 = tooth4484;
    }

    public MutableLiveData<String> getTooth4383() {
        return tooth4383;
    }

    public void setTooth4383(MutableLiveData<String> tooth4383) {
        this.tooth4383 = tooth4383;
    }

    public MutableLiveData<String> getTooth4282() {
        return tooth4282;
    }

    public void setTooth4282(MutableLiveData<String> tooth4282) {
        this.tooth4282 = tooth4282;
    }

    public MutableLiveData<String> getTooth4181() {
        return tooth4181;
    }

    public void setTooth4181(MutableLiveData<String> tooth4181) {
        this.tooth4181 = tooth4181;
    }

    public MutableLiveData<String> getTooth3171() {
        return tooth3171;
    }

    public void setTooth3171(MutableLiveData<String> tooth3171) {
        this.tooth3171 = tooth3171;
    }

    public MutableLiveData<String> getTooth3272() {
        return tooth3272;
    }

    public void setTooth3272(MutableLiveData<String> tooth3272) {
        this.tooth3272 = tooth3272;
    }

    public MutableLiveData<String> getTooth3373() {
        return tooth3373;
    }

    public void setTooth3373(MutableLiveData<String> tooth3373) {
        this.tooth3373 = tooth3373;
    }

    public MutableLiveData<String> getTooth3474() {
        return tooth3474;
    }

    public void setTooth3474(MutableLiveData<String> tooth3474) {
        this.tooth3474 = tooth3474;
    }

    public MutableLiveData<String> getTooth3575() {
        return tooth3575;
    }

    public void setTooth3575(MutableLiveData<String> tooth3575) {
        this.tooth3575 = tooth3575;
    }

    public MutableLiveData<String> getTooth36() {
        return tooth36;
    }

    public void setTooth36(MutableLiveData<String> tooth36) {
        this.tooth36 = tooth36;
    }

    public MutableLiveData<String> getTooth37() {
        return tooth37;
    }

    public void setTooth37(MutableLiveData<String> tooth37) {
        this.tooth37 = tooth37;
    }

    public MutableLiveData<String> getTooth38() {
        return tooth38;
    }

    public void setTooth38(MutableLiveData<String> tooth38) {
        this.tooth38 = tooth38;
    }

    public String getPlaqueScore() {
        return plaqueScore;
    }

    public void setPlaqueScore(String plaqueScore) {
        this.plaqueScore = plaqueScore;
    }

    public String getUrgencyOfTreatment() {
        return urgencyOfTreatment;
    }

    public void setUrgencyOfTreatment(String urgencyOfTreatment) {
        this.urgencyOfTreatment = urgencyOfTreatment;
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

    public void getUser() {
        String userId = firebaseAuth.getCurrentUser().getUid();
        //get user
        authRepository.getUser(userId, userLiveData);
    }

    public void composeDentistFinding(User user) {
        HashMap<String, String> dentitionStatusMap = new HashMap<>();
        dentitionStatusMap.put("tooth18", tooth18.getValue());
        dentitionStatusMap.put("tooth17", tooth17.getValue());
        dentitionStatusMap.put("tooth16", tooth16.getValue());
        dentitionStatusMap.put("tooth1555", tooth1555.getValue());
        dentitionStatusMap.put("tooth1454", tooth1454.getValue());
        dentitionStatusMap.put("tooth1353", tooth1353.getValue());
        dentitionStatusMap.put("tooth1252", tooth1252.getValue());
        dentitionStatusMap.put("tooth1151", tooth1151.getValue());
        dentitionStatusMap.put("tooth2161", tooth2161.getValue());
        dentitionStatusMap.put("tooth2262", tooth2262.getValue());
        dentitionStatusMap.put("tooth2363", tooth2363.getValue());
        dentitionStatusMap.put("tooth2464", tooth2464.getValue());
        dentitionStatusMap.put("tooth2565", tooth2565.getValue());
        dentitionStatusMap.put("tooth26", tooth26.getValue());
        dentitionStatusMap.put("tooth27", tooth27.getValue());
        dentitionStatusMap.put("tooth28", tooth28.getValue());
        dentitionStatusMap.put("tooth48", tooth48.getValue());
        dentitionStatusMap.put("tooth47", tooth47.getValue());
        dentitionStatusMap.put("tooth46", tooth46.getValue());
        dentitionStatusMap.put("tooth4585", tooth4585.getValue());
        dentitionStatusMap.put("tooth4484", tooth4484.getValue());
        dentitionStatusMap.put("tooth4383", tooth4383.getValue());
        dentitionStatusMap.put("tooth4282", tooth4282.getValue());
        dentitionStatusMap.put("tooth4181", tooth4181.getValue());
        dentitionStatusMap.put("tooth3171", tooth3171.getValue());
        dentitionStatusMap.put("tooth3272", tooth3272.getValue());
        dentitionStatusMap.put("tooth3373", tooth3373.getValue());
        dentitionStatusMap.put("tooth3474", tooth3474.getValue());
        dentitionStatusMap.put("tooth3575", tooth3575.getValue());
        dentitionStatusMap.put("tooth36", tooth36.getValue());
        dentitionStatusMap.put("tooth37", tooth37.getValue());
        dentitionStatusMap.put("tooth38", tooth38.getValue());

        setPlaqueScoreEntry();
        setUrgencyEntry();

        dentistFinding.setDentistName(user.getName());
        dentistFinding.setPatientName(patientName.getValue());
        dentistFinding.setDentitionStatus(dentitionStatusMap);
        dentistFinding.setPlaqueScore(plaqueScore);
        dentistFinding.setUrgencyOfTreatment(urgencyOfTreatment);

        dentistFindingLiveData.setValue(dentistFinding);
    }

    private void setPlaqueScoreEntry() {
        if (plaqueScore0.get()) {
            plaqueScore = "0";
        }
        else if (plaqueScore1.get()) {
            plaqueScore = "1";
        }
        else if (plaqueScore2.get()) {
            plaqueScore = "2";
        }
        else if (plaqueScore3.get()) {
            plaqueScore = "3";
        }
    }

    private void setUrgencyEntry() {
        if(urgency0.get()) {
            urgencyOfTreatment = "0";
        }
        else if(urgency1.get()) {
            urgencyOfTreatment = "1";
        }
        else if(urgency2.get()) {
            urgencyOfTreatment = "2";
        }
        else if(urgency3.get()) {
            urgencyOfTreatment = "3";
        }
        else if(urgency4.get()) {
            urgencyOfTreatment = "4";
        }
    }

    public void saveDentistFindingsData(DentistFinding dentistFinding) {
        dentistFindingsRepository.saveDentistFindings(dentistFinding,
                patientName.getValue(), patientId.getValue());
    }

}
