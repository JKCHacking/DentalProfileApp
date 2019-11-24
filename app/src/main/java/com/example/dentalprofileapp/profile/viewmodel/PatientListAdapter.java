package com.example.dentalprofileapp.profile.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.repository.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ProfileListViewHolder> {
    private List<Patient> patients = new ArrayList<>();

    @NonNull
    @Override
    public ProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new ProfileListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListViewHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        holder.imageViewProfilePicture.setBackgroundResource(currentPatient.getProfilePicture());
        holder.textViewPatientName.setText(currentPatient.getPatientName());
        holder.textViewBarangay.setText(currentPatient.getBarangay());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public static class ProfileListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewProfilePicture;
        private TextView textViewPatientName;
        private TextView textViewBarangay;

        public ProfileListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfilePicture = itemView.findViewById(R.id.profile_picture_iv);
            textViewPatientName = itemView.findViewById(R.id.patient_name_tv);
            textViewBarangay = itemView.findViewById(R.id.barangay_name_tv);
        }
    }
}
