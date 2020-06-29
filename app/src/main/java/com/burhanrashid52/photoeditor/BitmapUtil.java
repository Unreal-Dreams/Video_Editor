package com.burhanrashid52.photoeditor;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

class BitmapUtil {

    static Bitmap removeTransparency(Bitmap source, int width, int height) {
        int firstX = 0, firstY = 0;
        int lastX = width;
        int lastY = height;
        int[] pixels = new int[width * height];
        source.getPixels(pixels, 0, width, 0, 0, width, height);
        loop:
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pixels[x + (y * width)] != Color.TRANSPARENT) {
                    firstX = x;
                    break loop;
                }
            }
        }
        loop:
        for (int y = 0; y < height; y++) {
            for (int x = firstX; x < height; x++) {
                if (pixels[x + (y * width)] != Color.TRANSPARENT) {
                    firstY = y;
                    break loop;
                }
            }
        }
        loop:
        for (int x = width - 1; x >= firstX; x--) {
            for (int y = height - 1; y >= firstY; y--) {
                if (pixels[x + (y * width)] != Color.TRANSPARENT) {
                    lastX = x;
                    break loop;
                }
            }
        }
        loop:
        for (int y = height - 1; y >= firstY; y--) {
            for (int x = width - 1; x >= firstX; x--) {
                if (pixels[x + (y * width)] != Color.TRANSPARENT) {
                    lastY = y;
                    break loop;
                }
            }
        }
        Log.d("Bitmap", "removeTransparency: " + firstX + firstY);
        return Bitmap.createBitmap(source, 0, 0, lastX, lastY);
    }

    static Bitmap createBitmapFromGLSurface(GLSurfaceView glSurfaceView, GL10 gl) throws OutOfMemoryError {
        int x = 0, y = 0;
        int w = glSurfaceView.getWidth() ;
        int h = glSurfaceView.getHeight();
        int bitmapBuffer[] = new int[w * h];
        int bitmapSource[] = new int[w * h];
        IntBuffer intBuffer = IntBuffer.wrap(bitmapBuffer);
        intBuffer.position(0);

        try {
            gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, intBuffer);
            int offset1, offset2;
            for (int i = 0; i < h; i++) {
                offset1 = i * w;
                offset2 = (h - i - 1) * w;
                for (int j = 0; j < w; j++) {
                    int texturePixel = bitmapBuffer[offset1 + j];
                    int blue = (texturePixel >> 16) & 0xff;
                    int red = (texturePixel << 16) & 0x00ff0000;
                    int pixel = (texturePixel & 0xff00ff00) | red | blue;
                    bitmapSource[offset2 + j] = pixel;
                }
            }
        } catch (GLException e) {
            return null;
        }
        return Bitmap.createBitmap(bitmapSource, w, h, Bitmap.Config.ARGB_8888);
    }
}
