package com.rojas.sr.mlruntime;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.rojas.sr.Util;

import java.io.File;

public abstract class MLRuntime {

    protected String model_addr;
    protected String model_name;
    protected Context context;
    protected String TAG = "[MLRuntime]";

    public MLRuntime(String model_name, Context context){

        this.model_name = model_name;
        this.context = context;
        model_addr = new File(
                Util.assetFilePath(this.context, this.model_name)).getAbsolutePath();
        Log.d(this.TAG, "Loading model from: " + model_addr);

    }

    public abstract Bitmap forward(Bitmap input);

}
