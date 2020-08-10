package edu.uic.cs478.Project3II;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.InputQueue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

import static android.widget.ListPopupWindow.MATCH_PARENT;

public  class AttractionsActivity extends AppCompatActivity implements ListFragments.ListSelectionListener {
    public static String[] mTitleArray;
    public static String[] mWebArray;
    private FrameLayout mTitleFrameLayout;
    private  FrameLayout mWebFrameLayout;
    private FragmentManager mfragmentManager;
    private final WebFragment mWebFragment = new WebFragment();
    public int Index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);
        System.out.println("check1");
        Bundle bundle = new Bundle();
        bundle.putString("attraction","attraction");

        ListFragments listFragments = new ListFragments();
        listFragments.setArguments(bundle);
        WebFragment webFragment = new WebFragment();
        webFragment.setArguments(bundle);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Chicago tour guide");


        mTitleArray = getResources().getStringArray(R.array.attractions);
        mWebArray = getResources().getStringArray(R.array.attractionUrls);

        mTitleFrameLayout = (FrameLayout) findViewById(R.id.attraction_fragment_container);
        mWebFrameLayout = (FrameLayout) findViewById(R.id.attractionssite_fragment_container);

        mfragmentManager = getSupportFragmentManager();
        FragmentTransaction mfragmentTransaction = mfragmentManager.beginTransaction();
        mfragmentTransaction.replace(R.id.attraction_fragment_container,listFragments);
        mfragmentTransaction.replace(R.id.attractionssite_fragment_container,webFragment);

        mfragmentTransaction.commit();

        mfragmentManager.addOnBackStackChangedListener(

                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

    }


    public String getMyData(){
        return "attractions";
    }
    private void setLayout(){
        if(mWebFragment.isAdded()){
            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
                Log.i("porttrait1","portrait1");
                LayoutPortrait();
            }
            else{Log.i("landScape1","landScape1");
                LayoutLandScape();
            }
        }
        else{Log.i("layoutD1","layoutD1");

            LayoutDefault();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.attraction_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.Restaurant){
            Intent intent = new Intent(this,RestaurantsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @Override
    public void onListSelection(int index) {
        Index = index;
        //add fragment if it hasn't been added yet
        if (!mWebFragment.isAdded()) {
            //start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mfragmentManager.beginTransaction();

            //add the site fragment to the layout
            Bundle bundle = new Bundle();
            bundle.putString("attraction","attraction");
            mWebFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.attractionssite_fragment_container, mWebFragment);

            //add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            //commit the FragmentTransaction
            fragmentTransaction.commit();


            mfragmentManager.executePendingTransactions();
        }

        if (mWebFragment.getShownIndex() != index) {

            mWebFragment.showWebAtIndex(index);
        }

    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            //see if the siteFragment has been added
            if (mWebFragment.isAdded()) {
                //make the list of teams fill the entire screen
                LayoutPortrait();
            }
            else {
                LayoutDefault();


            }
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            //see if the siteFragment has been added
            if (mWebFragment.isAdded()) {
                LayoutLandScape();
                //make the list of teams fill the entire screen
                //match match 0 match
        }
            else {
                LayoutDefault();

            }
        }
    }
    private void LayoutPortrait(){Log.i("porttrait2","portrait2");
        mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,0f));
        mWebFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT,1f));
        Log.i("porttrait3","portrait3");

    }
    private void LayoutLandScape(){Log.i("landScape2","landScape2");
        mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,1f));
        mWebFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,MATCH_PARENT,2f));
        Log.i("landScape3","landScape3");


    }
    private void LayoutDefault(){Log.i("layoutD2","layoutD2");
        mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,MATCH_PARENT));
        mWebFrameLayout.setLayoutParams((new LinearLayout.LayoutParams(0,MATCH_PARENT)));
        Log.i("layoutD3","layoutD3");

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }
}
