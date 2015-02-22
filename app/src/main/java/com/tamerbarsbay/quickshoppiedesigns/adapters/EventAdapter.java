package com.tamerbarsbay.quickshoppiedesigns.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tamerbarsbay.quickshoppiedesigns.R;
import com.tamerbarsbay.quickshoppiedesigns.model.Event;

import java.util.ArrayList;

/**
 * Created by Tamer on 2/19/2015.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    private ArrayList<Event> items;
    private Context mContext;

    public EventAdapter(Context context, int textViewResourceId, ArrayList<Event> items) {
        super(context, textViewResourceId, items);
        mContext = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        Event event = items.get(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.event_list_item, null);
        }

        TextView titleText = (TextView) v.findViewById(R.id.event_list_title);
        TextView descriptionText = (TextView) v.findViewById(R.id.event_list_description);
        TextView dateText = (TextView) v.findViewById(R.id.event_list_date);
        TextView websiteText = (TextView) v.findViewById(R.id.event_list_website);
        TextView addressText = (TextView) v.findViewById(R.id.event_list_address);

        if (titleText != null) {
            titleText.setText(event.getTitle());
        }

        if (descriptionText != null) {
            descriptionText.setText(event.getDescription());
        }

        if (dateText != null) {
            dateText.setText(event.getDateAndTime());
        }

        if (websiteText != null) {
            websiteText.setText(event.getWebsite());
        }

        if (addressText != null) {
            addressText.setText(event.getAddress());
        }

        return v;
    }

}
