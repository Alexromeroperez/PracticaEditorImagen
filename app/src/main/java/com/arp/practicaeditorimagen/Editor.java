package com.arp.practicaeditorimagen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by Alex on 27/11/2015.
 */
public class Editor {
    public static int factorDeEscalado (int anchoBmp, int altoBmp,
                                        int anchoIv, int altoIv) {
        int factor = 1;
        if (altoBmp > altoIv || anchoBmp > anchoIv) {
            while ((altoBmp/factor) > altoIv && (anchoBmp/factor) > anchoIv) {
                factor = factor * 5;
            }
        }
        return factor;
    }
/*****Cambia a blanco y gris******/
    public static Bitmap toEscalaDeGris(Bitmap bmpOriginal) {
        Bitmap bmpGris = Bitmap.createBitmap(bmpOriginal.getWidth(),
                bmpOriginal.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas lienzo = new Canvas(bmpGris);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(cmcf);
        lienzo.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGris;
    }

    public static Bitmap rotarBitmap(Bitmap bmpOriginal, float angulo) {
        Matrix matriz = new Matrix();
        matriz.postRotate(angulo);
        return Bitmap.createBitmap(bmpOriginal, 0, 0,
                bmpOriginal.getWidth(), bmpOriginal.getHeight(), matriz, true);
    }

    public static Bitmap brillo(Bitmap bitmap,int brillo){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), bitmap.getConfig());
        int pixel, red, green, blue, alpha;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixel = bitmap.getPixel(i, j)+brillo;
                red = Color.red(pixel)+brillo;
                green = Color.green(pixel)+brillo;
                blue = Color.blue(pixel)+brillo;
                alpha = Color.alpha(pixel);
                bmp.setPixel(i, j, Color.argb(alpha, red, green, blue));
            }
        }

        return bmp;
    }
}
