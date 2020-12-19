package com.rojas.sr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;

import androidx.camera.core.ImageProxy;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Random;

public class Util {

    public static Bitmap toBitmap(Image image) {
        ByteBuffer buff = image.getPlanes()[0].getBuffer();
        buff.rewind();
        byte[] bytes = new byte[buff.capacity()];
        buff.get(bytes);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
