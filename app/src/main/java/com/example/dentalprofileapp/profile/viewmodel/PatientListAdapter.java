package com.example.dentalprofileapp.profile.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.view.ItemActionInterface;

import java.util.ArrayList;
import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ProfileListViewHolder> implements Filterable {
    private List<Patient> patients = new ArrayList<>();
    private List<Patient> patientsFull;
    private ItemActionInterface itemActionInterface;
    private String searchBy;

    public PatientListAdapter(Context context, String searchBy) {
        this.searchBy = searchBy;

        try {
            this.itemActionInterface = (ItemActionInterface)context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement ItemActionInterface.");
        }
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

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
        final String patientId = Integer.toString(currentPatient.getPatientId());

        holder.imageViewProfilePicture.setBackgroundResource(currentPatient.getProfilePicture());
        holder.textViewPatientName.setText(currentPatient.getPatientName());
        holder.textViewBarangay.setText(currentPatient.getBarangay());
        holder.textViewPatientId.setText(patientId);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("long pressed!");
                itemActionInterface.onLongClick(patientId);
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("single pressed!");
                //itemActionInterface.onClick();
                // pass the patientId of the selected patient.
                itemActionInterface.onClick(patientId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {

        System.out.println("setPatients()");
        System.out.println(patients);

        this.patients = patients;
        patientsFull = new ArrayList<>(patients);

        this.notifyDataSetChanged();
    }



    public static class ProfileListViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewProfilePicture;
        private TextView textViewPatientName;
        private TextView textViewBarangay;
        private TextView textViewPatientId;

        public ProfileListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfilePicture = itemView.findViewById(R.id.profile_picture_iv);
            textViewPatientName = itemView.findViewById(R.id.patient_name_tv);
            textViewBarangay = itemView.findViewById(R.id.barangay_name_tv);
            textViewPatientId = itemView.findViewById(R.id.patient_id_tv);
        }
    }

    @Override
    public Filter getFilter() {
        return patientsFilter;
    }

    private Filter patientsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Patient> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(patientsFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Patient patient : patientsFull) {
                    switch(searchBy) {
                        case "Patient Name":
                            if (patient.getPatientName().toLowerCase().contains(filterPattern)) {
                                filteredList.add(patient);
                            }
                            break;
                        case "Patient ID":
                            if (Integer.toString(patient.getPatientId()).contains(filterPattern)) {
                                filteredList.add(patient);
                            }
                            break;
                        case "Barangay":
                            if (patient.getBarangay().toLowerCase().contains(filterPattern)) {
                                filteredList.add(patient);
                            }
                            break;
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            patients.clear();
            patients.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
