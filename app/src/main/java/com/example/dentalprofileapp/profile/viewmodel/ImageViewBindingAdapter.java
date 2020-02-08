package com.example.dentalprofileapp.profile.viewmodel;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.example.dentalprofileapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageViewBindingAdapter {

    @BindingAdapter("bind:srcImage")
    public static void setSrcImage(ImageView imageView, String imageUrl) {

        if (imageUrl == null || imageUrl.equals("")) {
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        } else {
            if (imageUrl.contains("http")) {
                loadImageUrl(imageView, imageUrl);
            } else {
                File imageFile = new File(imageUrl);
                loadImageFile(imageView, imageFile);
            }
        }
    }

    @BindingAdapter("bind:srcImage2")
    public static void setSrcImage2(ImageView imageView, String imageUrl) {
        if (imageUrl == null || imageUrl.equals("")) {
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        } else {
            Picasso picasso = Picasso.get();
            picasso.setIndicatorsEnabled(true);
            picasso.load(imageUrl)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .resize(750,750)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            System.out.println("Image loaded successfully");
                        }

                        @Override
                        public void onError(Exception e) {
                            System.out.println("Image failed to load, error: " + e.getMessage());
                        }
                    });
        }
    }

    private static void loadImageUrl(ImageView imageView, String imageUrl) {
        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled(true);
        picasso.load(imageUrl)
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .resize(250,250)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("Image loaded successfully");
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("Image failed to load, error: " + e.getMessage());
                    }
                });
    }

    private static void loadImageFile(ImageView imageView, File imageFile) {
        Picasso picasso = Picasso.get();
        picasso.setIndicatorsEnabled(true);
        picasso.load(imageFile)
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp)
                .resize(250,250)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("Image loaded successfully");
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("Image failed to load, error: " + e.getMessage());
                    }
                });
    }
}
