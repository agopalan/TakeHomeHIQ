package com.example.ambika.webpicsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;

import java.util.List;

public class ImageArrayAdapter extends ArrayAdapter<ImageResults> {

    //constructor for gridview adapter
    public ImageArrayAdapter(Context context, List<ImageResults> imageResultsList){
        super(context, R.layout.image_results, imageResultsList);
    }

    //sets view for gridview
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageResults imageInfo = this.getItem(position);
        SmartImageView smartImageView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            smartImageView = (SmartImageView) inflater.inflate(
                    R.layout.image_results, parent, false);
        } else {
            smartImageView = (SmartImageView) convertView;
            smartImageView.setImageResource(android.R.color.transparent);
        }
        smartImageView.setImageUrl(imageInfo.getThumbnailURL());
        return smartImageView;
    }
}
