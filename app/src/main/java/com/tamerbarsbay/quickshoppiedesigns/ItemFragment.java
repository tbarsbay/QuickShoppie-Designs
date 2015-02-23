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
                    recentCats.add(new Category("0", "Bar", R.drawable.bar));
                    recentCats.add(new Category("1", "Restaurant", R.drawable.restaurant));

                    categories.add(new Category("0", "Grocery", R.drawable.groceryshop));
                    categories.add(new Category("1", "Restaurant", R.drawable.restaurant));
                    categories.add(new Category("2", "Bar", R.drawable.bar));
                    categories.add(new Category("3", "Pet Food", R.drawable.petfood));
                    categories.add(new Category("4", "Medicine", R.drawable.medicine));

                    categories.add(new Category("0", "Clothes", R.drawable.clothes));
                    categories.add(new Category("1", "Shoes", R.drawable.shoes));
                    categories.add(new Category("2", "Jewelry", R.drawable.jewelry));

                    categories.add(new Category("0", "Music", R.drawable.music));
                    categories.add(new Category("1", "Movie", R.drawable.movie));
                    categories.add(new Category("2", "Party", R.drawable.party));
                    categories.add(new Category("0", "Play", R.drawable.play));
                    categories.add(new Category("1", "Concert", R.drawable.concert));
                    categories.add(new Category("2", "Socialize", R.drawable.socialize));

                    categories.add(new Category("0", "Park", R.drawable.park));
                    categories.add(new Category("1", "Relax", R.drawable.relax));
                    categories.add(new Category("2", "Read", R.drawable.read));
                    categories.add(new Category("2", "Books", R.drawable.buybooks));

                    mAdapter1 = new CustomAdapter(getActivity(), R.layout.grid_item, recentCats);
                    break;
                case CAT_FOOD:
                    categories.add(new Category("0", "Grocery", R.drawable.groceryshop));
                    categories.add(new Category("1", "Restaurant", R.drawable.restaurant));
                    categories.add(new Category("2", "Bar", R.drawable.bar));
                    categories.add(new Category("3", "Pet Food", R.drawable.petfood));
                    categories.add(new Category("4", "Medicine", R.drawable.medicine));
                    break;
                case CAT_APPAREL:
                    categories.add(new Category("0", "Clothes", R.drawable.clothes));
                    categories.add(new Category("1", "Shoes", R.drawable.shoes));
                    categories.add(new Category("2", "Jewelry", R.drawable.jewelry));
                    break;
                case CAT_ENTERTAINMENT:
                    categories.add(new Category("0", "Music", R.drawable.music));
                    categories.add(new Category("1", "Movie", R.drawable.movie));
                    categories.add(new Category("2", "Party", R.drawable.party));
                    categories.add(new Category("0", "Play", R.drawable.play));
                    categories.add(new Category("1", "Concert", R.drawable.concert));
                    categories.add(new Category("2", "Socialize", R.drawable.socialize));
                    break;
                case CAT_OTHER:
                    categories.add(new Category("0", "Park", R.drawable.park));
                    categories.add(new Category("1", "Relax", R.drawable.relax));
                    categories.add(new Category("2", "Read", R.drawable.read));
                    categories.add(new Category("2", "Books", R.drawable.buybooks));
                    break;
            }

            mAdapter2 = new CustomAdapter(getActivity(), R.layout.grid_item, categories);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_grid, container, false);

        mListView1 = (AbsListView) view.findViewById(R.id.home_list1);

        TextView recentLabel = (TextView) view.findViewById(R.id.home_recent_label);
        TextView allLabel = (TextView) view.findViewById(R.id.home_all_label);
        View divider = view.findViewById(R.id.home_divider);

        if (mCategory != CAT_ALL) {
            mListView1.setVisibility(View.GONE);

            recentLabel.setVisibility(View.GONE);
            allLabel.setVisibility(View.GONE);
            divider.setVisibility(View.GONE);
        } else {
            mListView1.setOnItemClickListener(this);
            ((AdapterView<ListAdapter>) mListView1).setAdapter(mAdapter1);
            mListView1.setVisibility(View.VISIBLE);

            recentLabel.setVisibility(View.VISIBLE);
            allLabel.setVisibility(View.VISIBLE);
            divider.setVisibility(View.VISIBLE);
        }

        mListView2 = (AbsListView) view.findViewById(R.id.home_list2);
        ((AdapterView<ListAdapter>) mListView2).setAdapter(mAdapter2);
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
            //mListener.onFragmentInteraction("0");
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
        public void onFragmentInteraction(String id);
    }

}
