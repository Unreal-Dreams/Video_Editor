package com.burhanrashid52.photoeditor.filters;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.burhanrashid52.photoeditor.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class FilterViewAdapter extends RecyclerView.Adapter<FilterViewAdapter.ViewHolder> {

    private FilterListener mFilterListener;
    private List<Pair<String, FilterType>> mPairList = new ArrayList<>();

    public FilterViewAdapter(FilterListener filterListener) {
        mFilterListener = filterListener;
        setupFilters();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_filter_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, FilterType> filterPair = mPairList.get(position);
        Bitmap fromAsset = getBitmapFromAsset(holder.itemView.getContext(), filterPair.first);
        holder.mImageFilterView.setImageBitmap(fromAsset);
        holder.mTxtFilterName.setText(filterPair.second.name().replace("_", " "));
    }

    @Override
    public int getItemCount() {
        return mPairList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageFilterView;
        TextView mTxtFilterName;

        ViewHolder(View itemView) {
            super(itemView);
            mImageFilterView = itemView.findViewById(R.id.imgFilterView);
            mTxtFilterName = itemView.findViewById(R.id.txtFilterName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFilterListener.onFilterSelected(mPairList.get(getLayoutPosition()).second);
                }
            });
        }
    }

    private Bitmap getBitmapFromAsset(Context context, String strName) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(strName);
            return BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setupFilters() {
        mPairList.add(new Pair<>("filters/original.jpg", FilterType.DEFAULT));
        mPairList.add(new Pair<>("filters/auto_fix.png", FilterType.BRIGHTNESS));
        mPairList.add(new Pair<>("filters/brightness.png", FilterType.CONTRAST));
        mPairList.add(new Pair<>("filters/documentary.png", FilterType.EXPOSURE));
        mPairList.add(new Pair<>("filters/dual_tone.png", FilterType.FILTER_GROUP_SAMPLE));
        mPairList.add(new Pair<>("filters/fill_light.png", FilterType.GRAY_SCALE));
        mPairList.add(new Pair<>("filters/fish_eye.png", FilterType.MONOCHROME));
        mPairList.add(new Pair<>("filters/grain.png", FilterType.OPACITY));
        mPairList.add(new Pair<>("filters/gray_scale.png", FilterType.LUMINANCE));
        mPairList.add(new Pair<>("filters/lomish.png", FilterType.OVERLAY));
        mPairList.add(new Pair<>("filters/negative.png", FilterType.PIXELATION));
        mPairList.add(new Pair<>("filters/posterize.png", FilterType.RGB));
        mPairList.add(new Pair<>("filters/saturate.png", FilterType.SATURATION));
        mPairList.add(new Pair<>("filters/sepia.png", FilterType.SEPIA));
        mPairList.add(new Pair<>("filters/sharpen.png", FilterType.TONE_CURVE_SAMPLE));
        mPairList.add(new Pair<>("filters/temprature.png", FilterType.TONE));
        mPairList.add(new Pair<>("filters/vignette.png", FilterType.VIGNETTE));
        mPairList.add(new Pair<>("filters/cross_process.png", FilterType.WHITE_BALANCE));
    }
}
