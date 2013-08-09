package com.resto.utils;

import android.graphics.drawable.*;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import android.graphics.BitmapFactory;
import android.content.Context;

public class ImageUtils {

    public static void main(String args[]){

    }

    public static byte[] drawableToByteArray(Drawable d) {

        if (d != null) {
            Bitmap imageBitmap = ((BitmapDrawable) d).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        } else
            return null;

    }


    public static Drawable byteToDrawable(Context context, byte[] data) {

        if (data == null)
            return null;
        else
            return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(data, 0, data.length));
    }

}