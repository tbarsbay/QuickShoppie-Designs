package com.tamerbarsbay.quickshoppiedesigns.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tamerbarsbay.quickshoppiedesigns.R;
import com.tamerbarsbay.quickshoppiedesigns.model.Deal;

import java.util.ArrayList;

/**
 * Created by Tamer on 2/21/2015.
 */
public class DealAdapter extends ArrayAdapter<Deal> {
    private ArrayList<Deal> mDeals;
    private LayoutInflater mInflater;

    DisplayImageOptions options;

    public DealAdapter(Context context, int layoutResource, ArrayList<Deal> deals) {
        super(context, layoutResource, deals);
        mDeals = deals;
        mInflater = LayoutInflater.from(context);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().init(config);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = mInflater.inflate(R.layout.deal_list_item, null);
        }

        ImageView dealImage = (ImageView) v.findViewById(R.id.deal_image);
        TextView dealTitle = (TextView) v.findViewById(R.id.deal_title);
        TextView dealHost = (TextView) v.findViewById(R.id.deal_host);
        LinearLayout labelLayout = (LinearLayout) v.findViewById(R.id.deal_label_layout);

        Deal deal = mDeals.get(position);

        if (dealImage != null) {
            String imageUrl = deal.getImageUrl();
            ImageLoader.getInstance().displayImage(imageUrl, dealImage, options, null);
            //Log.d("Tamer", "Loading image: " + imageUrl);
        }

        if (dealTitle != null) {
            dealTitle.setText(deal.getTitle());
        }

        if (dealHost != null) {
            dealHost.setText(deal.getHost());
        }

        if (labelLayout != null) {
            labelLayout.setBackgroundColor(deal.getLabelBgColor());
        }

        return v;
    }
}
