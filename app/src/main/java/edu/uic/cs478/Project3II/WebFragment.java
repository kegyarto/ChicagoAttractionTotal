package edu.uic.cs478.Project3II;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class WebFragment extends Fragment {


    private int activity2 = 0;
    private WebView mwebView = null;
    private int mCurrIndex = -1;
    private int mWebArrlen;

    public int getShownIndex() {
        return mCurrIndex;
    }

    public void showWebAtIndex(int newIndex){
        if(newIndex < 0 || newIndex >= mWebArrlen){
            return;
        }
        mCurrIndex = newIndex;
        mwebView.getSettings().setJavaScriptEnabled(true);
        if(activity2 == 1) {
            mwebView.loadUrl(AttractionsActivity.mWebArray[mCurrIndex]);
        }
        if(activity2 == 2){
            mwebView.loadUrl(RestaurantsActivity.mWebArray[mCurrIndex]);
        }
    }
    @Override
    public void onAttach(Context activity) {

        super.onAttach(activity);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Web1");
        if(getArguments() != null){
            System.out.println("Web2");

            if(getArguments().getString("attraction") != null){
                System.out.println("Web3");
                if(getArguments().getString("attraction").equals("attraction")) {
                    System.out.println("Web4");
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

        return inflater.inflate(R.layout.fragment_web, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mwebView = (WebView) getActivity().findViewById(R.id.web);

        if(activity2 == 1) {
            mWebArrlen = AttractionsActivity.mWebArray.length;
        }
        if(activity2 == 2){
            mWebArrlen = RestaurantsActivity.mWebArray.length;
        }
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
