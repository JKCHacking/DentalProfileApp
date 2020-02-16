package com.example.dentalprofileapp.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.profile.entities.DentistFinding;
import com.example.dentalprofileapp.profile.view.ItemActionInterface;

import java.util.ArrayList;
import java.util.List;

public class DentistFindingAdapter extends RecyclerView.Adapter<DentistFindingAdapter.DentistFindingViewHolder> {
    private List<DentistFinding> dentistFindingsList = new ArrayList<>();
    private ItemActionInterface itemActionInterface;

    public DentistFindingAdapter(Context context) {
        try {
            this.itemActionInterface = (ItemActionInterface)context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement ItemActionInterface.");
        }
    }

    @NonNull
    @Override
    public DentistFindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dentist_findings_item, parent, false);
        return new DentistFindingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DentistFindingViewHolder holder, int position) {
        DentistFinding currentDentistFinding = dentistFindingsList.get(position);
        final String dentistName = currentDentistFinding.getDentistName();

        holder.textViewDentistName.setText(currentDentistFinding.getDentistName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("single pressed!");
                //itemActionInterface.onClick();
                // pass the patientId of the selected patient.
                itemActionInterface.onClick(dentistName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dentistFindingsList.size();
    }

    public void setDentistFindingsList(List<DentistFinding> dentistFindings) {
        this.dentistFindingsList = dentistFindings;
        this.notifyDataSetChanged();
    }

    public static class DentistFindingViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDentistName;
        public DentistFindingViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDentistName = itemView.findViewById(R.id.dentist_name);
        }
    }
}
