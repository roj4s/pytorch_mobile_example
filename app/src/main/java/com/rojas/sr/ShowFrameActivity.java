package com.rojas.sr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.rojas.sr.mlruntime.MLRuntime;
import com.rojas.sr.mlruntime.PytorchRuntime;
import com.rojas.sr.mlruntime.TfliteRuntime;

import java.io.File;

public class ShowFrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_frame);

        ImageView imageView = findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        String imagePath = extras.getString(Constants.PICTURE_PATH_KEY);
        boolean useTf = extras.getBoolean(Constants.USE_TF_KEY, false);


        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            String MODEL_ASSET_NAME = "mobile_model.pt";
            MLRuntime mlRuntime = useTf ? new TfliteRuntime(MODEL_ASSET_NAME, this) :
                    new PytorchRuntime(MODEL_ASSET_NAME, this);

            Bitmap image = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            int INPUT_TENSOR_WIDTH = 300;
            int INPUT_TENSOR_HEIGHT = 300;
            Bitmap croppedImage = Util.getCenterCropped(image, INPUT_TENSOR_WIDTH, INPUT_TENSOR_HEIGHT);
            Bitmap output = mlRuntime.forward(croppedImage);

            imageView.setImageBitmap(output);

        } else {
            System.out.println("IMAGE NOT FOUND AT: " + imagePath);
        }
    }
}