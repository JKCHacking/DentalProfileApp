package com.example.dentalprofileapp.profile.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.entities.Comorbidity;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.entities.PatientDentalImages;

@Database(entities = {Patient.class, Comorbidity.class, PatientDentalImages.class}, version = 1, exportSchema = false)
public abstract class PatientDatabase extends RoomDatabase {

    private static PatientDatabase instance;

    public abstract PatientDao patientDao();
    public abstract ComorbidityDao comorbidityDao();
    public abstract PatientDentalImagesDao patientDentalImagesDao();

    public static synchronized PatientDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PatientDatabase.class, "patient_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;

        private PopulateDbAsyncTask(PatientDatabase db) {
            patientDao = db.patientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Patient patient1 = new Patient();
            patient1.setProfilePicture(R.drawable.ic_launcher_foreground);
            patient1.setPatientId(1234);
            patient1.setDate("12/02/2019");
            patient1.setPatientName("Joshnee");
            patient1.setAge("23");
            patient1.setSex("Male");
            patient1.setOccupation("Engineer");
            patient1.setBarangay("Sta Maria");
            patient1.setPurok("Purok I");
            patient1.setAllergies("Chicken Joy");
            patient1.setPregnant(false);

            Patient patient2 = new Patient();
            patient2.setProfilePicture(R.drawable.ic_launcher_foreground);
            patient2.setPatientId(4567);
            patient2.setDate("12/02/2019");
            patient2.setPatientName("Mikee");
            patient2.setAge("23");
            patient2.setSex("Female");
            patient2.setOccupation("Medical Technologist");
            patient2.setBarangay("Sta Maria");
            patient2.setPurok("Purok II");
            patient2.setAllergies("Maarte");
            patient2.setPregnant(false);

            patientDao.insert(patient1);
            patientDao.insert(patient2);

            return null;
        }
    }
}
