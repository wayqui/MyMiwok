package com.example.android.mymiwok.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.mymiwok.R;
import com.example.android.mymiwok.dataobject.Word;

import java.util.List;

/**
 * Created by joselobm on 14/08/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int color;
    private Word palabraActual;


    public WordAdapter(Context context, List<Word> objects, int color) {
        super(context, 0, objects);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_item_layout, parent, false);
        }

        palabraActual = getItem(position);

        TextView mikowTextView = (TextView) listItemView.findViewById(R.id.text_mikow);
        mikowTextView.setText(palabraActual.getMiwokTraslation());

        TextView englishTextView = (TextView) listItemView.findViewById(R.id.text_english);
        englishTextView.setText(palabraActual.getDefaultTraslation());

        ImageView relatedImage = (ImageView) listItemView.findViewById(R.id.imagen);
        if (palabraActual.hasImage()) {
            relatedImage.setImageResource(palabraActual.getRelatedImageId());
            relatedImage.setVisibility(View.VISIBLE);
        } else {
            relatedImage.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int colorSel = ContextCompat.getColor(getContext(), this.color);
        textContainer.setBackgroundColor(colorSel);

        return listItemView;
    }
}
