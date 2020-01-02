package com.example.dentalprofileapp.profile.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.dentalprofileapp.R;
import com.example.dentalprofileapp.databinding.ActivityAddPatientBinding;
import com.example.dentalprofileapp.profile.entities.Patient;
import com.example.dentalprofileapp.profile.viewmodel.AddPatientViewModel;

public class AddPatientActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST = 1889;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1996;

    ActivityAddPatientBinding activityAddPatientBinding;
    AddPatientViewModel addPatientViewModel;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle extras = getIntent().getExtras();
        activityAddPatientBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_patient);
        addPatientViewModel = ViewModelProviders.of(this).get(AddPatientViewModel.class);

        activityAddPatientBinding.setViewmodel(addPatientViewModel);
        activityAddPatientBinding.setLifecycleOwner(this);

        if(extras != null) {
            String patientId = extras.getString("patientId");
            addPatientViewModel.populateDataToViews(patientId);
        }

        addPatientViewModel.getPatientHighestId().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                String patientId;
                if(extras == null) {
                    if (patient != null) {
                        patientId = Integer.toString(patient.getPatientId() + 1);
                    } else {
                        patientId = "1";
                    }
                } else {
                    patientId = extras.getString("patientId");
                    addPatientViewModel.setUpdate(true);
                }
                addPatientViewModel.getPatientId().setValue(patientId);
            }
        });

        addPatientViewModel.getOpenGalleryLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(intent, GALLERY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch(requestCode) {
                case GALLERY_REQUEST:
                    selectedImageUri = data.getData();
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permission to Read external storage not granted");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                                );
                    } else {
                        setSelectedImageToImageView();
                    }
                    break;
            }
        }
    }

    private String getPathFromUri(Uri selectedImageUri) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imageRealPath = cursor.getString(columnIndex);
        cursor.close();

        return imageRealPath;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setSelectedImageToImageView();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void setSelectedImageToImageView() {
        String selectedImageRealPath = getPathFromUri(selectedImageUri);

        int selectedImageView = addPatientViewModel.getOpenGalleryLiveData().getValue();
        switch(selectedImageView) {
            case 1:
                addPatientViewModel.getUrlUpperOcclusal().setValue(selectedImageRealPath);
                break;
            case 2:
                addPatientViewModel.getUrlLeftBuccal().setValue(selectedImageRealPath);
                break;
            case 3:
                addPatientViewModel.getUrlFront().setValue(selectedImageRealPath);
                break;
            case 4:
                addPatientViewModel.getUrlRightBuccal().setValue(selectedImageRealPath);
                break;
            case 5:
                addPatientViewModel.getUrlLowerOcclusal().setValue(selectedImageRealPath);
                break;
            case 6:
                addPatientViewModel.getUrlFrontFace().setValue(selectedImageRealPath);
                break;
        }
    }
}
