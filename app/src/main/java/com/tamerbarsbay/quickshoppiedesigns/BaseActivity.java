package com.tamerbarsbay.quickshoppiedesigns;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Inspired by BaseActivity in Google I/O 2014 source code.
 * @author Tamer Barsbay
 *
 */
public class BaseActivity extends ActionBarActivity {

    // Navigation drawer
    private DrawerLayout mDrawerLayout;

    private Handler mHandler;
    private ViewGroup mDrawerItemsListContainer;

    // Symbols for navigation drawer. Not necessarily the items present,
    // just all the possible items.
    protected static final int NAVDRAWER_ITEM_HYDE_PARK = 0;
    protected static final int NAVDRAWER_ITEM_REWARDS = 1;
    protected static final int NAVDRAWER_ITEM_DEALS = 2;
    protected static final int NAVDRAWER_ITEM_EVENTS = 3;
    protected static final int NAVDRAWER_ITEM_SHOPPING_CART = 4;
    protected static final int NAVDRAWER_ITEM_SETTINGS = 5;
    protected static final int NAVDRAWER_ITEM_INVALID = -1;
    protected static final int NAVDRAWER_ITEM_SEPARATOR = -2;

    // Titles for navigation drawer items (indices must correspond to those above)
    private static final int[] NAVDRAWER_TITLE_RES_ID = new int[] {
            R.string.nav_hyde_park,
            R.string.nav_rewards,
            R.string.nav_deals,
            R.string.nav_events,
            R.string.nav_shopping_cart,
            R.string.nav_settings
    };

    // Icons for navigation drawer items (indices must correspond to above array)
    private static final int[] NAVDRAWER_ICON_RES_ID = new int[] {
            R.drawable.hotel,
            R.drawable.star,
            R.drawable.coupon,
            R.drawable.event,
            R.drawable.cart,
            R.drawable.settings
    };

    // List of navigation drawer items that were actually added to the navdrawer, in order
    private ArrayList<Integer> mNavDrawerItems = new ArrayList<Integer>();

    // Views that correspond to each navdrawer item, null if not yet created
    private View[] mNavDrawerItemViews = null;

    // Toolbar and Drawer toggle
    private Toolbar mActionBarToolbar;

    // delay to launch nav drawer item, to allow close animation to play
    private static final int NAVDRAWER_LAUNCH_DELAY = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();

        if (savedInstanceState == null) {
            // register GCM client
        }

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        int id = getSelfNavDrawerItem();
        if (id == NAVDRAWER_ITEM_INVALID || id == NAVDRAWER_ITEM_SEPARATOR) {
            return;
        }
        if (!isSpecialItem(id)) {
            // change the active item on the nav drawer list
            setSelectedNavDrawerItem(id);
        }
    }

    /**
     * Returns the navigation drawer item that corresponds to this Activity. Subclasses
     * of BaseActivity override this to indicate what nav drawer item corresponds to them
     * Return NAVDRAWER_ITEM_INVALID to mean that this Activity should not have a Nav Drawer.
     */
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_INVALID;
    }

    /**
     * Sets up the navigation drawer.
     */
    private void setupNavDrawer() {
        // What nav drawer item should be selected?
        int selfItem = getSelfNavDrawerItem();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawerLayout == null) {
            return;
        }

        ScrollView navDrawer = (ScrollView)mDrawerLayout.findViewById(R.id.navdrawer);
        if (selfItem == NAVDRAWER_ITEM_INVALID) {
            // Don't show a navigation drawer
            if (navDrawer != null) {
                ((ViewGroup) navDrawer.getParent()).removeView(navDrawer);
            }
            mDrawerLayout = null;
            return;
        }

        // skipped something here - had to do with choosing accounts

        if (mActionBarToolbar != null) {
            mActionBarToolbar.setNavigationIcon(R.drawable.menu);
            mActionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            });
        }

        // skipped a lot here - listeners for the drawer opening and closing

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        // populate the navigation drawer
        populateNavDrawer();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolbar();
    }

    protected boolean isNavDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START);
    }

    protected void closeNavDrawer() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }

    /** Populates navigation Drawer */
    private void populateNavDrawer() {
        mNavDrawerItems.clear();

        mNavDrawerItems.add(NAVDRAWER_ITEM_HYDE_PARK);
        mNavDrawerItems.add(NAVDRAWER_ITEM_REWARDS);
        mNavDrawerItems.add(NAVDRAWER_ITEM_DEALS);
        mNavDrawerItems.add(NAVDRAWER_ITEM_EVENTS);
        mNavDrawerItems.add(NAVDRAWER_ITEM_SHOPPING_CART);
        mNavDrawerItems.add(NAVDRAWER_ITEM_SEPARATOR);
        mNavDrawerItems.add(NAVDRAWER_ITEM_SETTINGS);

        createNavDrawerItems();
    }

    @Override
    public void onBackPressed() {
        if (isNavDrawerOpen()) {
            closeNavDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void createNavDrawerItems() {
        mDrawerItemsListContainer = (ViewGroup) findViewById(R.id.navdrawer_items_list);
        if (mDrawerItemsListContainer == null) {
            return;
        }

        mNavDrawerItemViews = new View[mNavDrawerItems.size()];
        mDrawerItemsListContainer.removeAllViews();
        int i = 0;
        for (int itemId : mNavDrawerItems) {
            mNavDrawerItemViews[i] = makeNavDrawerItem(itemId, mDrawerItemsListContainer);
            mDrawerItemsListContainer.addView(mNavDrawerItemViews[i]);
            ++i;
        }
    }

    /**
     * Sets up the given navdrawer item's appearance to selected state.
     */
    private void setSelectedNavDrawerItem(int itemId) {
        if (mNavDrawerItemViews != null) {
            for (int i = 0; i < mNavDrawerItemViews.length; i++) {
                if (i < mNavDrawerItems.size()) {
                    int thisItemId = mNavDrawerItems.get(i);
                    formatNavDrawerItem(mNavDrawerItemViews[i], thisItemId, itemId == thisItemId);
                }
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setupNavDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
        }
        return true;
    }

    protected void goToNavDrawerItem(int item) {
        Intent intent;
        switch (item) {
            case NAVDRAWER_ITEM_HYDE_PARK:
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case NAVDRAWER_ITEM_REWARDS:
                intent = new Intent(this, RewardsActivity.class);
                startActivity(intent);
                break;
            case NAVDRAWER_ITEM_EVENTS:
                intent = new Intent(this, EventsActivity.class);
                startActivity(intent);
                break;
            case NAVDRAWER_ITEM_DEALS:
                intent = new Intent(this, DealsActivity.class);
                startActivity(intent);
                break;
            case NAVDRAWER_ITEM_SHOPPING_CART:
                intent = new Intent(this, CartActivity.class);
                startActivity(intent);
                break;
            case NAVDRAWER_ITEM_SETTINGS:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void onNavDrawerItemClicked(final int itemId) {
        if (itemId == getSelfNavDrawerItem()) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNavDrawerItem(itemId);
            }
        }, NAVDRAWER_LAUNCH_DELAY);

        if (!isSpecialItem(itemId)) {
            // change the active item on the nav drawer list
            setSelectedNavDrawerItem(itemId);
        }

        mDrawerLayout.closeDrawer(Gravity.START);
    }

    private boolean isSpecialItem(int itemId) {
        return itemId == NAVDRAWER_ITEM_SETTINGS;
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar == null) {
            mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolbar != null) {
                setSupportActionBar(mActionBarToolbar);
            }
        }
        return mActionBarToolbar;
    }

    private View makeNavDrawerItem(final int itemId, ViewGroup container) {
        boolean selected = getSelfNavDrawerItem() == itemId;
        int layoutToInflate = 0;
        if (itemId == NAVDRAWER_ITEM_SEPARATOR) {
            layoutToInflate = R.layout.navdrawer_item_separator;
        } else {
            layoutToInflate = R.layout.navdrawer_item;
        }
        View view = getLayoutInflater().inflate(layoutToInflate, container, false);

        if (itemId == NAVDRAWER_ITEM_SEPARATOR) {
            return view;
        }

        ImageView iconView = (ImageView)view.findViewById(R.id.navdrawer_item_icon);
        TextView titleView = (TextView)view.findViewById(R.id.navdrawer_item_title);
        int iconId = itemId >= 0 && itemId < NAVDRAWER_ICON_RES_ID.length ?
                NAVDRAWER_ICON_RES_ID[itemId] : 0;
        int titleId = itemId >= 0 && itemId < NAVDRAWER_TITLE_RES_ID.length ?
                NAVDRAWER_TITLE_RES_ID[itemId] : 0;

        iconView.setVisibility(iconId > 0 ? View.VISIBLE : View.GONE);
        if (itemId >= 0) {
            iconView.setImageResource(iconId);
        }
        titleView.setText(getString(titleId));

        formatNavDrawerItem(view, itemId, selected);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onNavDrawerItemClicked(itemId);
            }
        });

        return view;
    }

    private void formatNavDrawerItem(View view, int itemId, boolean selected) {
        if (itemId == NAVDRAWER_ITEM_SEPARATOR) {
            return;
        }

        ImageView iconView = (ImageView) view.findViewById(R.id.navdrawer_item_icon);
        TextView titleView = (TextView) view.findViewById(R.id.navdrawer_item_title);

        // determine appearance based on whether item is selected
        Resources r = getResources();
        iconView.setColorFilter(selected ?
                r.getColor(R.color.navdrawer_icon_tint_selected) :
                r.getColor(R.color.navdrawer_icon_tint));
        titleView.setTextColor(selected ?
                r.getColor(R.color.navdrawer_text_color_selected) :
                r.getColor(R.color.navdrawer_text_color));
        view.setBackgroundColor(selected ?
                r.getColor(R.color.navdrawer_selected_background) :
                r.getColor(android.R.color.transparent));
    }
}
