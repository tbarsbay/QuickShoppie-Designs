package com.tamerbarsbay.quickshoppiedesigns;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.tamerbarsbay.quickshoppiedesigns.adapters.DealAdapter;
import com.tamerbarsbay.quickshoppiedesigns.model.Deal;

import java.util.ArrayList;


public class DealsActivity extends BaseActivity {

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_DEALS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deals);

        String dinnerUrl = "http://www.casperfry.com/wp-content/uploads/2013/02/dinner.jpg";
        String coffeeUrl = "http://www.wallstreetotc.com/wp-content/uploads/2015/02/coffee-cup.jpg";
        String drinksUrl = "http://blog.braudcommunications.com/wp-content/uploads/bar-congress-drinks.jpg";

        ArrayList<Deal> deals = new ArrayList<Deal>();
        deals.add(new Deal("Dinner for Two", "Chant Restaurant", Color.parseColor("#81ad02"), dinnerUrl));
        deals.add(new Deal("Happy Hour Drinks", "The Promontory", Color.parseColor("#c52704"), drinksUrl));
        deals.add(new Deal("Happy Hour Drinks", "The Promontory", Color.parseColor("#c52704"), drinksUrl));
        deals.add(new Deal("Free Coffee", "The Sip and Savor", Color.parseColor("#bf6b23"), coffeeUrl));
        deals.add(new Deal("Free Coffee", "The Sip and Savor", Color.parseColor("#bf6b23"), coffeeUrl));
        deals.add(new Deal("Dinner for Two", "Chant Restaurant", Color.parseColor("#81ad02"), dinnerUrl));

        DealAdapter adapter = new DealAdapter(this, R.layout.deal_list_item, deals);
        GridView gridView = (GridView) findViewById(R.id.deals_gridview);
        gridView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deals, menu);
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
