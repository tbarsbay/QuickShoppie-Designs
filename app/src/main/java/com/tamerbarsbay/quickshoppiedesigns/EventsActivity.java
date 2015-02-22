package com.tamerbarsbay.quickshoppiedesigns;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tamerbarsbay.quickshoppiedesigns.adapters.EventAdapter;
import com.tamerbarsbay.quickshoppiedesigns.model.Event;

import java.util.ArrayList;


public class EventsActivity extends BaseActivity {

    @Override
    protected int getSelfNavDrawerItem() {
        return NAVDRAWER_ITEM_EVENTS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ArrayList<Event> events = new ArrayList<Event>();
        events.add(new Event("Mardi Gras Celebration", "A celebration of Mardi Gras sponsored by the University of Chicago.", "Feb 20 - 9:00pm - 12:00am", "www.uchicago.edu/mardigras", "1212 E 59th St, Chicago, Illinois 60637"));
        events.add(new Event("Event Title Here", "This is where the event description goes. A brief sentence or two about what the event is going to be.", "Feb 2, 2015 3:00pm - 4:30pm", "www.eventwebsite.com/details", "5460 South Harper Ave, Chicago, IL 60615"));
        events.add(new Event("Event Title Here", "This is where the event description goes. A brief sentence or two about what the event is going to be.", "Feb 2, 2015 3:00pm - 4:30pm", "www.eventwebsite.com/details", "5460 South Harper Ave, Chicago, IL 60615"));
        events.add(new Event("Event Title Here", "This is where the event description goes. A brief sentence or two about what the event is going to be.", "Feb 2, 2015 3:00pm - 4:30pm", "www.eventwebsite.com/details", "5460 South Harper Ave, Chicago, IL 60615"));
        events.add(new Event("Event Title Here", "This is where the event description goes. A brief sentence or two about what the event is going to be.", "Feb 2, 2015 3:00pm - 4:30pm", "www.eventwebsite.com/details", "5460 South Harper Ave, Chicago, IL 60615"));
        events.add(new Event("Event Title Here", "This is where the event description goes. A brief sentence or two about what the event is going to be.", "Feb 2, 2015 3:00pm - 4:30pm", "www.eventwebsite.com/details", "5460 South Harper Ave, Chicago, IL 60615"));

        EventAdapter adapter = new EventAdapter(this, R.layout.event_list_item, events);
        ListView lv = (ListView) findViewById(R.id.events_listview);
        lv.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
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
