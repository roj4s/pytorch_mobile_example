package com.rojas.sr.mlruntime;

import android.content.Context;
import android.graphics.Bitmap;

import com.rojas.sr.Util;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;
import org.pytorch.torchvision.TensorImageUtils;

public class PytorchRuntime extends MLRuntime {

    private final Module model;
    private final String TAG = "[PytorchRuntime]";
    private final float[] ones = {1.0f, 1.0f, 1.0f};

    public PytorchRuntime(String model_name, Context context){
        super(model_name, context);

        this.model = Module.load(this.model_addr);
    }

    public Bitmap forward(Bitmap input) {
        Tensor inputTensor = TensorImageUtils.bitmapToFloat32Tensor(input, this.ones, this.ones);
        final Tensor outputTensor = this.model.forward(IValue.from(inputTensor)).toTensor();
        long[] output_shape = outputTensor.shape();
        final float[] outputTensorAsFloatArray = outputTensor.getDataAsFloatArray();

        return Util.bitmapFromRGBImageAsFloatArray(outputTensorAsFloatArray, (int)output_shape[2], (int)output_shape[3]);
    }

}
