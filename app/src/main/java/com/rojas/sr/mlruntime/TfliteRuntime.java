package com.rojas.sr.mlruntime;

import android.content.Context;
import android.graphics.Bitmap;

public class TfliteRuntime extends MLRuntime {

    public TfliteRuntime(String model_name, Context context) {
        super(model_name, context);
    }

    @Override
    public Bitmap forward(Bitmap input) {
        /* TODO */
        return input;
    }
}
