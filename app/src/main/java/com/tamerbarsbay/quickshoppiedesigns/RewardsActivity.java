package com.tamerbarsbay.quickshoppiedesigns;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tamerbarsbay.quickshoppiedesigns.adapters.RewardAdapter;
import com.tamerbarsbay.quickshoppiedesigns.model.Reward;

import java.util.ArrayList;


public class RewardsActivity extends BaseActivity {

    /*
    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_REWARDS;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        ArrayList<Reward> rewards = new ArrayList<Reward>();
        rewards.add(new Reward("Treasure Island", "35", true));
        rewards.add(new Reward("Bonne Sante", "20", false));
        rewards.add(new Reward("Starbucks", "135", true));
        rewards.add(new Reward("The Sip and Savor", "5", false));
        rewards.add(new Reward("Walgreens", "40", false));

        RewardAdapter adapter = new RewardAdapter(this, R.layout.reward_list_item, rewards);
        ListView lv = (ListView) findViewById(R.id.rewards_listview);
        lv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rewards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
