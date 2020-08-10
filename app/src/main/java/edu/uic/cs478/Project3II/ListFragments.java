package edu.uic.cs478.Project3II;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ListFragments extends ListFragment {

    private ListSelectionListener mListener = null;
    private int activity2 = 0;

    // Callback interface that allows this Fragment to notify the QuoteViewerActivity when
    // user clicks on a List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        getListView().setItemChecked(pos, true);


        mListener.onListSelection(pos);
    }

    @Override
    public void onAttach(Context activity) {

        super.onAttach(activity);

        try {

            // Set the ListSelectionListener for communicating with the QuoteViewerActivity
            // Try casting the containing activity to a ListSelectionListener
            mListener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {
            // Cast failed: This is not going to work because containing activity may not
            // have implemented onListSelection() method
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        //Intent intent = getIntent

        String attraction = "Attractions";
        setRetainInstance(true);


        System.out.println("right here");



    }

    // UB:  Notice that the superclass's method does an OK job of inflating the
    //      container layout.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("right here1");


        if(getArguments() != null){
            String text = getArguments().getString("attraction");
            if(getArguments().getString("attraction") != null){
                if(getArguments().getString("attraction").equals("attraction")) {
                    activity2 = 1;
                }
            }
            else if(getArguments().getString("restaurant") != null){
                if (getArguments().getString("restaurant").equals("restaurant")) {
                    System.out.println("Check1");
                    activity2 = 2;
                }
            }
          }

        System.out.println("here");


        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedState) {

        super.onActivityCreated(savedState);


        // Set the list adapter for the ListView
        // Discussed in more detail in the user interface classes lesson
        if(activity2 == 1){
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_list, AttractionsActivity.mTitleArray));
        }
        if(activity2 == 2){
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_list,RestaurantsActivity.mTitleArray));
        }
        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }
}
