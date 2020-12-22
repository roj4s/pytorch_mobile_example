package com.rojas.sr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {

    public static String assetFilePath(Context context, String assetName) {
        File file = new File(context.getFilesDir(), assetName);
        if (file.exists() && file.length() > 0) {
            return file.getAbsolutePath();
        }

        try (InputStream is = context.getAssets().open(assetName)) {
            try (OutputStream os = new FileOutputStream(file)) {
                byte[] buffer = new byte[4 * 1024];
                int read;
                while ((read = is.read(buffer)) != -1) {
                    os.write(buffer, 0, read);
                }
                os.flush();
            }
            return file.getAbsolutePath();
        } catch (IOException e) {
            Log.e(Constants.TAG, "Error process asset " + assetName + " to file path");
        }
        return null;
    }

    public static Bitmap bitmapFromRGBImageAsFloatArray(float[] data, int width, int height){

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        float max = 0;
        float min = 999999;

        for(float f: data){
            if(f > max){
                max = f;
            }
            if(f < min){
                min = f;
            }
        }

        int delta = (int)(max - min);

        for (int i = 0; i < width * height; i++) {

            int r = (int) ((data[i] - min)/delta*255.0f);
            int g = (int) ((data[i + width * height] - min) / delta*255.0f);
            int b = (int) ((data[i + width * height * 2] - min) / delta*255.0f);

            int x = i / width;
            int y = i % width;

            int color = Color.rgb(r, g, b);
            bmp.setPixel(x, y, color);

        }
        return bmp;
    }

    public static Bitmap getCenterCropped(Bitmap input, int width, int height){

        int x = input.getWidth() / 2 - width / 2;
        int y = input.getHeight() / 2 - height / 2;

        return Bitmap.createBitmap(input, x, y, width, height);

    }

}
