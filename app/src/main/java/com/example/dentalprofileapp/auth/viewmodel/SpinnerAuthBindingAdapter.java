package com.example.dentalprofileapp.auth.viewmodel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class SpinnerAuthBindingAdapter {

    @BindingAdapter(value = {"app:userType",
            "app:userTypeAttrChanged"}, requireAll = false)
    public static void setUserType(final AppCompatSpinner spinner,
                                 final String selectedUserType,
                                 final InverseBindingListener changeListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeListener.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                changeListener.onChange();
            }
        });
        if (selectedUserType != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(selectedUserType);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "app:userType",
            event = "app:userTypeAttrChanged")
    public static String getUserType(final AppCompatSpinner spinner) {
        System.out.println("Spinner selected value: " + (String)spinner.getSelectedItem());
        return (String)spinner.getSelectedItem();
    }
}
