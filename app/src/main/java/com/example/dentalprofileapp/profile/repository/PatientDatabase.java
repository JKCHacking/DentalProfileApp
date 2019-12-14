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

@Database(entities = {Patient.class, Comorbidity.class}, version = 1, exportSchema = false)
public abstract class PatientDatabase extends RoomDatabase {

    private static PatientDatabase instance;

    public abstract PatientDao patientDao();
    public abstract ComorbidityDao comorbidityDao();

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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;

        private PopulateDbAsyncTask(PatientDatabase db) {
            patientDao = db.patientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.insert(new Patient(R.drawable.ic_launcher_foreground,
                    1234,
                    "12/02/2019",
                    "Joshnee",
                    "23",
                    "Male",
                    "Engineer",
                    "Sta Maria",
                    "purok I",
                    "Chicken Joy",
                    false));

            patientDao.insert(new Patient(R.drawable.ic_launcher_foreground,
                    4567,
                    "12/02/2019",
                    "Mikee",
                    "23",
                    "Female",
                    "Medical Technologist",
                    "Sta Maria",
                    "purok II",
                    "Maarte",
                    false));

            patientDao.insert(new Patient(R.drawable.ic_launcher_foreground,
                    8910,
                    "12/02/2019",
                    "Kim",
                    "23",
                    "Male",
                    "Engineer",
                    "Sta Maria",
                    "purok I",
                    "Chicken Joy",
                    false));

            return null;
        }
    }
}
