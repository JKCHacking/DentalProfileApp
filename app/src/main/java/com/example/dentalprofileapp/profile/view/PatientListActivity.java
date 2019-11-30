package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.repository.Patient;
import com.example.dentalprofileapp.profile.viewmodel.PatientListAdapter;
import com.example.dentalprofileapp.profile.viewmodel.PatientListViewModel;

import java.util.List;

public class PatientListActivity extends AppCompatActivity {
    private PatientListViewModel patientListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.profile_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PatientListAdapter adapter = new PatientListAdapter();
        recyclerView.setAdapter(adapter);

        patientListViewModel = ViewModelProviders.of(this).get(PatientListViewModel.class);
        patientListViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                adapter.setPatients(patients);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
