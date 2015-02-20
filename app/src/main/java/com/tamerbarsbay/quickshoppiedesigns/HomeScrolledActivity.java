package com.tamerbarsbay.quickshoppiedesigns;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;


public class HomeScrolledActivity extends BaseActivity implements ItemFragment.OnFragmentInteractionListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager = null;
    SlidingTabLayout mSlidingTabLayout = null;

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_HYDE_PARK;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_scrolled);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_scrolled, menu);
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

    @Override
    public void onFragmentInteraction(String id) {
        Toast.makeText(this, "id is: " + id, Toast.LENGTH_SHORT).show();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ItemFragment.newInstance("string1", "string2");
                case 1:
                    return ItemFragment.newInstance("string1", "string2");
                case 2:
                    return ItemFragment.newInstance("string1", "string2");
                case 3:
                    return ItemFragment.newInstance("string1", "string2");
                case 4:
                    return ItemFragment.newInstance("string1", "string2");
                default:
                    return ItemFragment.newInstance("string1", "string2");
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "ALL";
                case 1:
                    return "FOOD";
                case 2:
                    return "APPAREL";
                case 3:
                    return "ENTERTAINMENT";
                case 4:
                    return "OTHER";
            }
            return null;
        }
    }
}
