package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityDentistFindingListBinding;
import com.example.dentalprofileapp.profile.adapter.DentistFindingAdapter;
import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.viewmodel.DentistFindingViewModel;
import com.example.dentalprofileapp.utils.ToastUtil;

import java.util.List;

public class DentistFindingActivity extends AppCompatActivity implements ItemActionInterface {

    private ActivityDentistFindingListBinding activityDentistFindingListBinding;
    private DentistFindingViewModel dentistFindingViewModel;
    private DentistFindingAdapter adapter;
    private ToastUtil toastUtil;
    private String patientName;
    private String patientId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toastUtil = new ToastUtil(this);

        final Bundle extras = getIntent().getExtras();
        patientName = extras.getString("patientName");
        patientId = extras.getString("patientId");

        activityDentistFindingListBinding = DataBindingUtil.setContentView(this, R.layout.activity_dentist_finding_list);
        activityDentistFindingListBinding.setLifecycleOwner(this);

        dentistFindingViewModel = ViewModelProviders.of(this).get(DentistFindingViewModel.class);
        activityDentistFindingListBinding.setViewmodel(dentistFindingViewModel);
        dentistFindingViewModel.getDentistFindingByPatientName(patientName);

        RecyclerView recyclerView = findViewById(R.id.dentist_findings_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new DentistFindingAdapter(this);
        recyclerView.setAdapter(adapter);

        dentistFindingViewModel.dentistFindingListLiveData.observe(this, new Observer<List<DentistFinding>>() {
            @Override
            public void onChanged(List<DentistFinding> dentistFindings) {
                if(dentistFindings != null) {
                    adapter.setDentistFindingsList(dentistFindings);
                    toastUtil.createToastMessage("Dentist Findings retrieved");
                }
            }
        });
    }

    @Override
    public void onLongClick(String data) {

    }

    @Override
    public void onClick(String data) {
        Intent intent = new Intent(this, DentistResultActivity.class);
        intent.putExtra("patientId", patientId);
        intent.putExtra("patientName", patientName);
        intent.putExtra("dentistName", data);
        this.startActivity(intent);
    }

    @Override
    public void onTouch(String patientId, String patientName) {

    }
}
