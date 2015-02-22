package com.tamerbarsbay.quickshoppiedesigns.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tamerbarsbay.quickshoppiedesigns.R;
import com.tamerbarsbay.quickshoppiedesigns.StoreDetailsActivity;
import com.tamerbarsbay.quickshoppiedesigns.dummy.DummyContent;

import java.util.ArrayList;

/**
 * Created by Tamer on 2/12/2015.
 */
public class CustomAdapter extends ArrayAdapter<DummyContent.DummyItem> {

    private ArrayList<DummyContent.DummyItem> items;
    private Context mContext;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<DummyContent.DummyItem> items) {
        super(context, textViewResourceId, items);
        mContext = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        DummyContent.DummyItem item = items.get(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_item, null);
        }

        if (position == 0) {
            // make the first item go to the StoreDetailsActivity just for demonstration purposes
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, StoreDetailsActivity.class));
                }
            });
        }

        TextView title = (TextView) v.findViewById(R.id.grid_text);
        ImageView icon = (ImageView) v.findViewById(R.id.grid_icon);

        if (title != null) {
            title.setText(item.title);
        }

        if (icon != null) {
            icon.setImageDrawable(mContext.getResources().getDrawable(item.iconResource));
        }

        return v;
    }

}
