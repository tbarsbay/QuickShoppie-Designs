package com.tamerbarsbay.quickshoppiedesigns.dummy;

import com.tamerbarsbay.quickshoppiedesigns.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static ArrayList<DummyItem> ITEMS_RECENT = new ArrayList<DummyItem>();
    public static ArrayList<DummyItem> ITEMS_ALL = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        addItem(ITEMS_RECENT, new DummyItem("1", "Bar", R.drawable.store));
        addItem(ITEMS_RECENT, new DummyItem("2", "Restaurant", R.drawable.restaurant));
        addItem(ITEMS_ALL, new DummyItem("1", "Grocery Store", R.drawable.cart));
        addItem(ITEMS_ALL, new DummyItem("2", "Restaurant", R.drawable.restaurant));
        addItem(ITEMS_ALL, new DummyItem("3", "Fast Food", R.drawable.store));
        addItem(ITEMS_ALL, new DummyItem("4", "Bar", R.drawable.store));
        addItem(ITEMS_ALL, new DummyItem("5", "Pharmacy", R.drawable.store));
    }

    private static void addItem(ArrayList<DummyItem> list, DummyItem item) {
        list.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String title;
        public int iconResource;

        public DummyItem(String id, String content, int iconResourceId) {
            this.id = id;
            this.title = content;
            this.iconResource = iconResourceId;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
