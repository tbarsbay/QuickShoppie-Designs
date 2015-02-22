package com.tamerbarsbay.quickshoppiedesigns.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tamerbarsbay.quickshoppiedesigns.R;
import com.tamerbarsbay.quickshoppiedesigns.model.Reward;

import java.util.ArrayList;

/**
 * Created by Tamer on 2/21/2015.
 */
public class RewardAdapter extends ArrayAdapter<Reward> {

    private ArrayList<Reward> mRewards;
    private LayoutInflater mInflater;

    public RewardAdapter(Context context, int textViewResourceId, ArrayList<Reward> rewards) {
        super(context, textViewResourceId, rewards);
        mInflater = LayoutInflater.from(context);
        mRewards = rewards;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        Reward reward = mRewards.get(position);

        if (v == null) {
            v = mInflater.inflate(R.layout.reward_list_item, null);
        }

        TextView storeText = (TextView) v.findViewById(R.id.reward_store);
        TextView userPtsText = (TextView) v.findViewById(R.id.reward_userPoints);
        LinearLayout rewardLayout = (LinearLayout) v.findViewById(R.id.reward_layout);
        ImageView checkMark = (ImageView) v.findViewById(R.id.reward_checkmark);

        if (storeText != null) {
            storeText.setText(reward.getStoreName());
        }

        if (userPtsText != null) {
            userPtsText.setText(reward.getUserPoints() + "pts");
        }

        if (rewardLayout != null) {
            if (reward.isDealAvailable()) {
                rewardLayout.setBackgroundColor(Color.parseColor("#A5D6A7"));
                checkMark.setVisibility(View.VISIBLE);
            } else {
                rewardLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
                checkMark.setVisibility(View.INVISIBLE);
            }
        }

        return v;
    }

}
