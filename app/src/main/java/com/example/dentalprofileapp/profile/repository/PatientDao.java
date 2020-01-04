package com.example.dentalprofileapp.profile.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dentalprofileapp.profile.entities.Patient;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert
    long insert(Patient patient);

    @Query("UPDATE patient_table SET " +
            "mPatientId = :patientId, " +
            "profilePicture = :profilePicture, " +
            "mDate = :date," +
            "mPatientName = :patientName," +
            "mAge = :age," +
            "mSex = :sex," +
            "mOccupation = :occupation," +
            "mBarangay = :barangay," +
            "mPurok = :purok," +
            "mAllergies = :allergies," +
            "mPregnant = :pregnant " +
            "WHERE mPatientId = :patientId")
    void update(int patientId,
                int profilePicture,
                String date,
                String patientName,
                String age,
                String sex,
                String occupation,
                String barangay,
                String purok,
                String allergies,
                Boolean pregnant);

    @Delete
    void delete(Patient patient);

    @Query("DELETE FROM patient_table WHERE mPatientId = :patientId")
    void deleteByPatientId(int patientId);

    @Query("DELETE FROM patient_table")
    void deleteAllPatients();

    @Query("SELECT * FROM patient_table ORDER BY mPatientName ASC")
    List<Patient> getAllPatientsOrderPatientName();

    @Query("SELECT * FROM patient_table ORDER BY mBarangay ASC")
    List<Patient> getAllPatientsOrderBarangay();

    @Query("SELECT * FROM patient_table ORDER BY mPatientId ASC")
    List<Patient> getAllPatientsOrderPatientId();

    @Query("SELECT * FROM patient_table ORDER BY mPatientId DESC LIMIT 0, 1")
    LiveData<Patient> getPatientWithHighestId();

    @Query("SELECT * FROM patient_table WHERE mPatientId = :patientId")
    Patient getPatientByPatientId(int patientId);
}
