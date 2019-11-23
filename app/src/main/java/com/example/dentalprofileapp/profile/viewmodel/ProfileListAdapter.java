package com.example.dentalprofileapp.profile.viewmodel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder> {

    @NonNull
    @Override
    public ProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ProfileListViewHolder extends RecyclerView.ViewHolder {
        public ImageView mProfilePicture;
        public TextView mProfileName;
        public TextView mBarangayName;

        public ProfileListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
