package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityPatientListBinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.adapter.PatientListAdapter;
import com.example.dentalprofileapp.profile.viewmodel.PatientListViewModel;
import com.example.dentalprofileapp.profile.viewmodel.SwipeController;
import com.example.dentalprofileapp.profile.viewmodel.SwipeControllerActions;
import com.example.dentalprofileapp.utils.ToastUtil;

import java.util.List;

public class PatientListActivity extends AppCompatActivity implements ItemActionInterface, SwipeControllerActions {
    private PatientListViewModel patientListViewModel;
    private ActivityPatientListBinding activityPatientListBinding;
    private PatientListAdapter adapter;
    private ToastUtil toastUtil;
    private String patientId;
    private String patientName;
    private SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate()");
        super.onCreate(savedInstanceState);

        toastUtil = new ToastUtil(this);

        activityPatientListBinding = DataBindingUtil.setContentView(this, R.layout.activity_patient_list);
        activityPatientListBinding.setLifecycleOwner(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientListViewModel = ViewModelProviders.of(this).get(PatientListViewModel.class);
        activityPatientListBinding.setViewmodel(patientListViewModel);

        RecyclerView recyclerView = findViewById(R.id.profile_list_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        swipeController = new SwipeController(this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        adapter = new PatientListAdapter(this, patientListViewModel.getSearchBy().getValue());
        recyclerView.setAdapter(adapter);

        patientListViewModel.getAllPatientsMutableData().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                if (patients != null) {
                    adapter.setPatients(patients);
                } else {
                    System.out.println("No Patients retrieved");
                    toastUtil.createToastMessage("No Patients retrieved");
                }
            }
        });

        patientListViewModel.getSortBy().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                patientListViewModel.setAllPatients();
            }
        });

        patientListViewModel.getSearchBy().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
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
        patientListViewModel.setAllPatients();
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

    @Override
    public void onTouch(String dataPatientId, String dataPatientName) {
        System.out.println("OnTouch() PatientId: " + dataPatientId);
        patientId = dataPatientId;
        patientName = dataPatientName;
    }

    @Override
    public void onRightClicked(int position) {
        System.out.println("Checking up patientId " + patientId);
        Intent intent = new Intent(this, DentistCheckUpActivity.class);
        intent.putExtra("patientId", patientId);
        this.startActivity(intent);
    }

    @Override
    public void onLeftClicked(int position) {
        Intent intent = new Intent(this, DentistFindingActivity.class);
        intent.putExtra("patientId", patientId);
        intent.putExtra("patientName", patientName);
        this.startActivity(intent);
    }
}
