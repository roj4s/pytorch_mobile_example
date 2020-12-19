package com.rojas.sr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class ShowFrameActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_frame);

        imageView = findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        String imagePath = extras.getString("picture_path");

        File imgFile = new File(imagePath);

        if(imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);

        } else {
            System.out.println("IMAGE NOT FOUND AT: " + imagePath);
        }

    }
}