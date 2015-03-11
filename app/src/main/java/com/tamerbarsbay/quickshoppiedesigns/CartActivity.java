package com.tamerbarsbay.quickshoppiedesigns;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tamerbarsbay.quickshoppiedesigns.model.CartItem;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class CartActivity extends BaseActivity {

    private ArrayList<CartItem> mCartItems;
    private ViewGroup mCartItemsList;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_SHOPPING_CART;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle("My Cart");

        mCartItems = new ArrayList<CartItem>();
        mCartItems.add(new CartItem("Ketchup", 2, 5.99));
        mCartItems.add(new CartItem("Mayo", 1, 3.59));
        mCartItems.add(new CartItem("Tomato", 5, .99));
        mCartItems.add(new CartItem("Milk", 1, 3.99));
        mCartItems.add(new CartItem("Wine", 3, 6.99));

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);

        double subtotal = 0;
        for (CartItem item : mCartItems) {
            subtotal += item.getTotalCost();
        }
        TextView subtotalText = (TextView) findViewById(R.id.cart_subtotal);
        subtotalText.setText("$" + df.format(subtotal));

        double taxRate = 0.07;
        double totalTax = subtotal*taxRate;
        TextView taxText = (TextView) findViewById(R.id.cart_tax);
        taxText.setText("$" + df.format(totalTax));

        double total = subtotal*(1+taxRate);
        TextView totalText = (TextView) findViewById(R.id.cart_total_cost);
        totalText.setText("$" + df.format(total));

        createCartItems();

        LinearLayout deliverLayout = (LinearLayout) findViewById(R.id.cart_layout_deliver);
        deliverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), DeliveryActivity.class));
            }
        });
    }

    private void createCartItems() {
        mCartItemsList = (ViewGroup) findViewById(R.id.cart_items_list);

        for (CartItem item : mCartItems) {
            mCartItemsList.addView(makeCartItem(item, mCartItemsList));
        }
    }

    private View makeCartItem(CartItem item, ViewGroup container) {
        View v = getLayoutInflater().inflate(R.layout.cart_list_item, container, false);

        TextView titleText = (TextView) v.findViewById(R.id.cart_item_title);
        TextView quantityText = (TextView) v.findViewById(R.id.cart_item_quantity);
        TextView totalCostText = (TextView) v.findViewById(R.id.cart_item_total_cost);

        if (titleText != null) {
            titleText.setText(item.getTitle());
        }

        if (quantityText != null) {
            quantityText.setText(String.valueOf(item.getQuantity()));
        }

        if (totalCostText != null) {
            totalCostText.setText("$" + String.valueOf(item.getTotalCost()));
        }

        return v;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
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
        } else if (id == R.id.action_add_item) {
            // startActivity(new Intent(this, SearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
