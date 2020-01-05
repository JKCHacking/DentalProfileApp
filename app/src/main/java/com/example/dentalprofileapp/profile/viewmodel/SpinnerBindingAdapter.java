package com.example.dentalprofileapp.profile.viewmodel;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class SpinnerBindingAdapter {

    @BindingAdapter(value = {"bind:pmtOpt",
            "bind:pmtOptAttrChanged"}, requireAll = false)
    public static void setPmtOpt(final AppCompatSpinner spinner,
                                 final String selectedPmtOpt,
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
        if (selectedPmtOpt != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(selectedPmtOpt);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:pmtOpt",
            event = "bind:pmtOptAttrChanged")
    public static String getPmtOpt(final AppCompatSpinner spinner) {
        System.out.println("Spinner selected value: " + (String)spinner.getSelectedItem());
        return (String)spinner.getSelectedItem();
    }

    @BindingAdapter(value = {"bind:sortBy",
            "bind:sortByAttrChanged"}, requireAll = false)
    public static void setSortBy(final AppCompatSpinner spinner,
                                 final String selectedSortBy,
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
        if (selectedSortBy != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(selectedSortBy);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:sortBy",
            event = "bind:sortByAttrChanged")
    public static String getSortBy(final AppCompatSpinner spinner) {
        System.out.println("Spinner selected value: " + (String)spinner.getSelectedItem());
        return (String)spinner.getSelectedItem();
    }

    @BindingAdapter(value = {"bind:searchBy",
            "bind:searchByAttrChanged"}, requireAll = false)
    public static void setSearchBy(final AppCompatSpinner spinner,
                                 final String selectedSearchBy,
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
        if (selectedSearchBy != null) {
            int pos = ((ArrayAdapter<String>) spinner.getAdapter()).getPosition(selectedSearchBy);
            spinner.setSelection(pos, true);
        }
    }

    @InverseBindingAdapter(attribute = "bind:searchBy",
            event = "bind:searchByAttrChanged")
    public static String getSearchBy(final AppCompatSpinner spinner) {
        System.out.println("Spinner selected value: " + (String)spinner.getSelectedItem());
        return (String)spinner.getSelectedItem();
    }
}
