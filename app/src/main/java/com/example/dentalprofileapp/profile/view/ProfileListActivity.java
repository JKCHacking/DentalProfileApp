package com.example.dentalprofileapp.profile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.repository.PatientItem;

import java.util.ArrayList;

public class ProfileListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        ArrayList<PatientItem> patientItemList = new ArrayList<>();
        patientItemList.add(new PatientItem(R.drawable.ic_launcher_foreground, "Joshnee", "Pasonanca"));
        patientItemList.add(new PatientItem(R.drawable.ic_launcher_foreground, "Mikee", "Sta Maria"));
        patientItemList.add(new PatientItem(R.drawable.ic_launcher_foreground, "Kim", "Divisoria"));
    }
}
