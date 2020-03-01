package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.repository.DentistFindingsRepository;

import java.util.HashMap;

public class DentistResultViewModel extends AndroidViewModel {
    private DentistFindingsRepository  repository;
    public MutableLiveData<DentistFinding> dentistFindingLiveData;

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
    private MutableLiveData<String> calculusScore = new MutableLiveData<>();
    private MutableLiveData<String> urgencyOfTreatment = new MutableLiveData<>();

    public DentistResultViewModel(@NonNull Application application) {
        super(application);

        repository = new DentistFindingsRepository();
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

    public MutableLiveData<String> getCalculusScore() {
        return calculusScore;
    }

    public void setCalculusScore(MutableLiveData<String> calculusScore) {
        this.calculusScore = calculusScore;
    }

    public MutableLiveData<String> getUrgencyOfTreatment() {
        return urgencyOfTreatment;
    }

    public void setUrgencyOfTreatment(MutableLiveData<String> urgencyOfTreatment) {
        this.urgencyOfTreatment = urgencyOfTreatment;
    }

    public void getDentistFindingsByDocumentId(String dentistName, String patientName, String patientId) {
        String documentId = dentistName + "_" + patientName + "_" + patientId;
        repository.getDentistFindingsByDocumentId(documentId, dentistFindingLiveData);
    }

    public void populateDentistResultToView(DentistFinding dentistFinding) {
        calculusScore.setValue(dentistFinding.getCalculusScore());
        urgencyOfTreatment.setValue(dentistFinding.getUrgencyOfTreatment());

        HashMap<String, String> dentitionStatusMap;
        dentitionStatusMap = dentistFinding.getDentitionStatus();

        tooth18.setValue(dentitionStatusMap.get("tooth18"));
        tooth17.setValue(dentitionStatusMap.get("tooth17"));
        tooth16.setValue(dentitionStatusMap.get("tooth16"));
        tooth1555.setValue(dentitionStatusMap.get("tooth1555"));
        tooth1454.setValue(dentitionStatusMap.get("tooth1454"));
        tooth1353.setValue(dentitionStatusMap.get("tooth1353"));
        tooth1252.setValue(dentitionStatusMap.get("tooth1252"));
        tooth1151.setValue(dentitionStatusMap.get("tooth1151"));
        tooth2161.setValue(dentitionStatusMap.get("tooth2161"));
        tooth2262.setValue(dentitionStatusMap.get("tooth2262"));
        tooth2363.setValue(dentitionStatusMap.get("tooth2363"));
        tooth2464.setValue(dentitionStatusMap.get("tooth2464"));
        tooth2565.setValue(dentitionStatusMap.get("tooth2565"));
        tooth26.setValue(dentitionStatusMap.get("tooth26"));
        tooth27.setValue(dentitionStatusMap.get("tooth27"));
        tooth28.setValue(dentitionStatusMap.get("tooth28"));

        tooth48.setValue(dentitionStatusMap.get("tooth48"));
        tooth47.setValue(dentitionStatusMap.get("tooth47"));
        tooth46.setValue(dentitionStatusMap.get("tooth46"));
        tooth4585.setValue(dentitionStatusMap.get("tooth4585"));
        tooth4484.setValue(dentitionStatusMap.get("tooth4484"));
        tooth4383.setValue(dentitionStatusMap.get("tooth4383"));
        tooth4282.setValue(dentitionStatusMap.get("tooth4282"));
        tooth4181.setValue(dentitionStatusMap.get("tooth4181"));
        tooth3171.setValue(dentitionStatusMap.get("tooth3171"));
        tooth3272.setValue(dentitionStatusMap.get("tooth3272"));
        tooth3373.setValue(dentitionStatusMap.get("tooth3373"));
        tooth3474.setValue(dentitionStatusMap.get("tooth3474"));
        tooth3575.setValue(dentitionStatusMap.get("tooth3575"));
        tooth36.setValue(dentitionStatusMap.get("tooth36"));
        tooth37.setValue(dentitionStatusMap.get("tooth37"));
        tooth38.setValue(dentitionStatusMap.get("tooth38"));
    }
}
