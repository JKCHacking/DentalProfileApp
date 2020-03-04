package com.example.dentalprofileapp.profile.adapter;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.viewmodel.ItemCheckListener;

import java.util.ArrayList;
import java.util.List;

public class UploadListAdapter extends RecyclerView.Adapter<UploadListAdapter.UploadListViewHolder> {
    private List<Patient> uploadPatientList = new ArrayList<>();
    private ItemCheckListener itemCheckListener;

    public UploadListAdapter(ItemCheckListener itemCheckListener) {
        this.itemCheckListener = itemCheckListener;
    }

    @NonNull
    @Override
    public UploadListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upload_list_item, parent, false);
        return new UploadListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final UploadListViewHolder holder, int position) {
        Patient currentUploadPatient = uploadPatientList.get(position);
        final String patientName = currentUploadPatient.getPatientName();
        final String patientId = Integer.toString(currentUploadPatient.getPatientId());

        holder.textViewPatientName.setText(patientName);
        holder.textViewPatientId.setText(patientId);

//        holder.setOnclickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.checkBox.setChecked(!holder.checkBox.isChecked());
//                if(holder.checkBox.isChecked()) {
//                    itemCheckListener.onItemCheck(patientId);
//                } else {
//                    itemCheckListener.onItemUncheck(patientId);
//                }
//            }
//        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()) {
                    itemCheckListener.onItemCheck(patientId);
                } else {
                    itemCheckListener.onItemUncheck(patientId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploadPatientList.size();
    }

    public void setUploadPatientList(List<Patient> uploadPatientList) {
        this.uploadPatientList = uploadPatientList;
        this.notifyDataSetChanged();
    }

    public static class UploadListViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPatientName;
        private TextView textViewPatientId;
        public CheckBox checkBox;
        public View itemView;

        public UploadListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textViewPatientName = itemView.findViewById(R.id.upload_patient_name);
            textViewPatientId = itemView.findViewById(R.id.upload_patient_id);
            checkBox = itemView.findViewById(R.id.upload_checkbox);
        }

        public void setOnclickListener(View.OnClickListener onclickListener) {
            itemView.setOnClickListener(onclickListener);
            checkBox.setOnClickListener(onclickListener);
        }
    }
}
