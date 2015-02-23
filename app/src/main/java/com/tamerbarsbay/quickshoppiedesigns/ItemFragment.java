package com.tamerbarsbay.quickshoppiedesigns;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.tamerbarsbay.quickshoppiedesigns.adapters.CustomAdapter;
import com.tamerbarsbay.quickshoppiedesigns.dummy.DummyContent;
import com.tamerbarsbay.quickshoppiedesigns.model.Category;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static final String ARG_CATEGORY = "category";

    public static final int CAT_ALL = 0;
    public static final int CAT_FOOD = 1;
    public static final int CAT_APPAREL = 2;
    public static final int CAT_ENTERTAINMENT = 3;
    public static final int CAT_OTHER = 4;

    private int mCategory = -1;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView1;
    private AbsListView mListView2;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private CustomAdapter mAdapter1;
    private CustomAdapter mAdapter2;

    // TODO: Rename and change types of parameters
    public static ItemFragment newInstance(int category) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCategory = getArguments().getInt(ARG_CATEGORY);
        }

        ArrayList<Category> categories = new ArrayList<Category>();
        ArrayList<Category> recentCats = new ArrayList<Category>();

        if (mCategory != -1) {
            switch (mCategory) {
                case CAT_ALL:
                    recentCats.add(new Category("0", "Bar", R.drawable.store));
                    recentCats.add(new Category("1", "Restaurant", R.drawable.restaurant));
                    break;
                case CAT_FOOD:
                    break;
                case CAT_APPAREL:
                    break;
                case CAT_ENTERTAINMENT:
                    break;
                case CAT_OTHER:
                    break;
            }
        }

        // TODO: Change Adapter to display your content
        mAdapter1 = new CustomAdapter(getActivity(), R.layout.grid_item, DummyContent.ITEMS_RECENT);
        mAdapter2 = new CustomAdapter(getActivity(), R.layout.grid_item, DummyContent.ITEMS_ALL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_grid, container, false);

        // Set the adapter
        mListView1 = (AbsListView) view.findViewById(R.id.home_list1);
        ((AdapterView<ListAdapter>) mListView1).setAdapter(mAdapter1);

        mListView2 = (AbsListView) view.findViewById(R.id.home_list2);
        ((AdapterView<ListAdapter>) mListView2).setAdapter(mAdapter2);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView1.setOnItemClickListener(this);
        mListView2.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(DummyContent.ITEMS_RECENT.get(position).id);
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView1.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
