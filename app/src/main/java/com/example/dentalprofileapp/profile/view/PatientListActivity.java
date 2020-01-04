package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityPatientListBinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.viewmodel.PatientListAdapter;
import com.example.dentalprofileapp.profile.viewmodel.PatientListViewModel;

import java.util.List;

public class PatientListActivity extends AppCompatActivity implements ItemActionInterface {
    private PatientListViewModel patientListViewModel;
    private ActivityPatientListBinding activityPatientListBinding;
    private PatientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate()");
        super.onCreate(savedInstanceState);
        activityPatientListBinding = DataBindingUtil.setContentView(this, R.layout.activity_patient_list);
        activityPatientListBinding.setLifecycleOwner(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.profile_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        patientListViewModel = ViewModelProviders.of(this).get(PatientListViewModel.class);
        activityPatientListBinding.setViewmodel(patientListViewModel);

        adapter = new PatientListAdapter(this, patientListViewModel.getSortBy().getValue());
        recyclerView.setAdapter(adapter);

        patientListViewModel.getAllPatientsMutableData().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                adapter.setPatients(patients);
            }
        });

        patientListViewModel.getSortBy().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                patientListViewModel.setAllPatients();
                adapter.setSearchBy(s);
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.search_patient_searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println("onQueryTextChanged()");
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
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

    @Override
    public void onLongClick(String data) {
        final int patientId = Integer.parseInt(data);

        // display dialog here.
        new AlertDialog.Builder(this)
                .setTitle("Delete Record")
                .setMessage("Are you sure you want to delete this patient?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        patientListViewModel.deleteByPatientId(patientId);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void onClick(String data) {
        //call the AddPatientActivity
        //add an intent extras

        Intent intent = new Intent(this, AddPatientActivity.class);
        intent.putExtra("patientId", data);
        this.startActivity(intent);
    }
}
