package com.burhanrashid52.photoeditor.filters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.daasuu.epf.filter.GlBilateralFilter;
import com.daasuu.epf.filter.GlBoxBlurFilter;
import com.daasuu.epf.filter.GlBrightnessFilter;
import com.daasuu.epf.filter.GlBulgeDistortionFilter;
import com.daasuu.epf.filter.GlCGAColorspaceFilter;
import com.daasuu.epf.filter.GlContrastFilter;
import com.daasuu.epf.filter.GlCrosshatchFilter;
import com.daasuu.epf.filter.GlExposureFilter;
import com.daasuu.epf.filter.GlFilter;
import com.daasuu.epf.filter.GlFilterGroup;
import com.daasuu.epf.filter.GlGammaFilter;
import com.daasuu.epf.filter.GlGaussianBlurFilter;
import com.daasuu.epf.filter.GlGrayScaleFilter;
import com.daasuu.epf.filter.GlHalftoneFilter;
import com.daasuu.epf.filter.GlHazeFilter;
import com.daasuu.epf.filter.GlHighlightShadowFilter;
import com.daasuu.epf.filter.GlHueFilter;
import com.daasuu.epf.filter.GlInvertFilter;
import com.daasuu.epf.filter.GlLookUpTableFilter;
import com.daasuu.epf.filter.GlLuminanceFilter;
import com.daasuu.epf.filter.GlLuminanceThresholdFilter;
import com.daasuu.epf.filter.GlMonochromeFilter;
import com.daasuu.epf.filter.GlOpacityFilter;
import com.daasuu.epf.filter.GlPixelationFilter;
import com.daasuu.epf.filter.GlPosterizeFilter;
import com.daasuu.epf.filter.GlRGBFilter;
import com.daasuu.epf.filter.GlSaturationFilter;
import com.daasuu.epf.filter.GlSepiaFilter;
import com.daasuu.epf.filter.GlSharpenFilter;
import com.daasuu.epf.filter.GlSolarizeFilter;
import com.daasuu.epf.filter.GlSphereRefractionFilter;
import com.daasuu.epf.filter.GlSwirlFilter;
import com.daasuu.epf.filter.GlToneCurveFilter;
import com.daasuu.epf.filter.GlToneFilter;
import com.daasuu.epf.filter.GlVibranceFilter;
import com.daasuu.epf.filter.GlVignetteFilter;
import com.daasuu.epf.filter.GlWatermarkFilter;
import com.daasuu.epf.filter.GlWeakPixelInclusionFilter;
import com.daasuu.epf.filter.GlWhiteBalanceFilter;
import com.daasuu.epf.filter.GlZoomBlurFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sudamasayuki on 2017/05/18.
 */

public enum FilterType {
    DEFAULT,
    BRIGHTNESS,
    CONTRAST,
    EXPOSURE,
    FILTER_GROUP_SAMPLE,
    GRAY_SCALE,
    LUMINANCE,
    MONOCHROME,
    OPACITY,
    OVERLAY,
    PIXELATION,
    POSTERIZE,
    RGB,
    SATURATION,
    SEPIA,
    TONE_CURVE_SAMPLE,
    TONE,
    VIGNETTE,
    WHITE_BALANCE,
    ;


 //   public static List<FilterType> createFilterList() {
 //       return Arrays.asList(FilterType.values());
  //  }

//    public static GlFilter createGlFilter(FilterType filterType, Context context) {
//        switch (filterType) {
//            case DEFAULT:
//                return new GlFilter();
//            case SEPIA:
//                return new GlSepiaFilter();
//            case GRAY_SCALE:
//                return new GlGrayScaleFilter();
//            case INVERT:
//                return new GlInvertFilter();
//            case HAZE:
//                return new GlHazeFilter();
//            case MONOCHROME:
//                return new GlMonochromeFilter();
//            case BILATERAL_BLUR:
//                return new GlBilateralFilter();
//            case BOX_BLUR:
//                return new GlBoxBlurFilter();
//            case LOOK_UP_TABLE_SAMPLE:
//                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.lookup_sample);
//
//                return new GlLookUpTableFilter(bitmap);
//            case TONE_CURVE_SAMPLE:
//                try {
//                    InputStream is = context.getAssets().open("acv/tone_cuver_sample.acv");
//                    return new GlToneCurveFilter(is);
//                } catch (IOException e) {
//                    Log.e("FilterType", "Error");
//                }
//                return new GlFilter();
//
//            case SPHERE_REFRACTION:
//                return new GlSphereRefractionFilter();
//            case VIGNETTE:
//                return new GlVignetteFilter();
//            case FILTER_GROUP_SAMPLE:
//                return new GlFilterGroup(new GlSepiaFilter(), new GlVignetteFilter());
//            case GAUSSIAN_FILTER:
//                return new GlGaussianBlurFilter();
//            case BULGE_DISTORTION:
//                return new GlBulgeDistortionFilter();
//            case CGA_COLORSPACE:
//                return new GlCGAColorspaceFilter();
//            case SHARP:
//                GlSharpenFilter glSharpenFilter = new GlSharpenFilter();
//                glSharpenFilter.setSharpness(4f);
//                return glSharpenFilter;
//            case BITMAP_OVERLAY_SAMPLE:
//                return new GlBitmapOverlaySample(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round));
//            default:
//                return new GlFilter();
//        }
//    }


    public static GlFilter createGlFilter(FilterType filterType, Context context) {
        switch (filterType) {
            case DEFAULT:
                return new GlFilter();
            case BRIGHTNESS:
                GlBrightnessFilter glBrightnessFilter = new GlBrightnessFilter();
                glBrightnessFilter.setBrightness(0.2f);
                return glBrightnessFilter;
            case CONTRAST:
                GlContrastFilter glContrastFilter = new GlContrastFilter();
                glContrastFilter.setContrast(2.5f);
                return glContrastFilter;
            case EXPOSURE:
                return new GlExposureFilter();
            case FILTER_GROUP_SAMPLE:
                return new GlFilterGroup(new GlSepiaFilter(), new GlVignetteFilter());
            case GRAY_SCALE:
                return new GlGrayScaleFilter();

            case MONOCHROME:
                return new GlMonochromeFilter();
            case OPACITY:
                return new GlOpacityFilter();
            case PIXELATION:
                return new GlPixelationFilter();
            case POSTERIZE:
                return new GlPosterizeFilter();
            case RGB:
                GlRGBFilter glRGBFilter = new GlRGBFilter();
                glRGBFilter.setRed(0f);
                return glRGBFilter;
            case SATURATION:
                return new GlSaturationFilter();
            case SEPIA:
                return new GlSepiaFilter();

            case TONE_CURVE_SAMPLE:
                try {
                    InputStream is = context.getAssets().open("acv/tone_cuver_sample.acv");
                    return new GlToneCurveFilter(is);
                } catch (IOException e) {
                    Log.e("FilterType", "Error");
                }
                return new GlFilter();
            case TONE:
                return new GlToneFilter();

            case VIGNETTE:
                return new GlVignetteFilter();
            case WHITE_BALANCE:
                GlWhiteBalanceFilter glWhiteBalanceFilter = new GlWhiteBalanceFilter();
                glWhiteBalanceFilter.setTemperature(2400f);
                glWhiteBalanceFilter.setTint(2f);
                return glWhiteBalanceFilter;
            default:
                return new GlFilter();
        }
    }


}

