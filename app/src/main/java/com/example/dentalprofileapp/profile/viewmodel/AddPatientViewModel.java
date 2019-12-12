package com.example.dentalprofileapp.profile.viewmodel;

import android.app.Application;
import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.repository.PatientRepository;

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

    //Observables
    public final ObservableBoolean isMale;
    public final ObservableBoolean isFemale;
    public final ObservableBoolean isPregnant;

    //helper variables
    private LiveData<Patient> patientHighestId;
    private ArrayList<String> comorbidityNameList = new Arraylist<>();
    private ArrayList<Comorbidity> comorbidityList = new ArrayList<>();

    //entities
    private Patient mPatient;
    private Comorbidity mComorbidity;

    //Repository references
    private PatientRepository patientRepository;
    private ComorbidityRepository comorbidityRepository;

    public AddPatientViewModel(@NonNull Application application) {
        super(application);

        patientRepository = new PatientRepository(application);
        comorbidityRepository = new ComorbidityRepository(Application);

        isMale = new ObservableBoolean();
        isFemale = new ObservableBoolean();
        isPregnant = new ObservableBoolean();
        patientHighestId = repository.getPatientWithHighestId();
        patientId = new MutableLiveData<>();

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

    public void insert(View view) {
        System.out.println("Insert method called!");

        composeEntities();

        patientRepository.insert(mPatient);
        comorbidityRepository.insert(comorbidityList);
    }

    private void composeEntities() {
        //TODO: Generate PatientId
        int patientIdInt = Integer.parseInt(patientId.getValue());

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
            comorbidityList.put(new Comorbidity(patientIdInt, comorbidityName));
        }
    }

    public void onClickCheckBox(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        //if checkbox is checked
        if (checked) {
            // get the text from the checkbox and put in the arraylist.
            comorbidityNameList.put(view.getText().toString());
        } else { //if checkbox is not checked
            // remove the text from the arraylist.
            comorbidityNameList.remove(view.getText().toString());
        }
    }

    @BindingAdapter(value = {"bind:pmtOpt",
            "bind:pmtOptAttrChanged"}, requireAll = false)
    public static void setPmtOpt(final AppCompatSpinner spinner,
                                 final String selectedPmtOpt,
                                 final InverseBindingListener changeListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeListener.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                changeListener.onChange();
            }
        });
        if (selectedPmtOpt != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(selectedPmtOpt);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:pmtOpt",
            event = "bind:pmtOptAttrChanged")
    public static String getPmtOpt(final AppCompatSpinner spinner) {
        System.out.println("Spinner selected value: " + (String)spinner.getSelectedItem());
        return (String)spinner.getSelectedItem();
    }
}
