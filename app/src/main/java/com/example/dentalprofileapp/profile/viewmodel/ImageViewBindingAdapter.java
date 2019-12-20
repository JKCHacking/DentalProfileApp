package com.example.dentalprofileapp.profile.viewmodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.example.dentalprofileapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

public class ImageViewBindingAdapter {

    @BindingAdapter("bind:srcImage")
    public static void setSrcImage(ImageView imageView, String imageUrl) {

        if (imageUrl == null || imageUrl.equals("")) {
            imageView.setImageResource(R.drawable.ic_launcher_foreground);
        } else {
            File imageFile = new File(imageUrl);
            Picasso picasso = Picasso.get();
            picasso.setIndicatorsEnabled(true);
            picasso.load(imageFile)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
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
}

class CropSquareTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override public String key() { return "square()"; }
}
