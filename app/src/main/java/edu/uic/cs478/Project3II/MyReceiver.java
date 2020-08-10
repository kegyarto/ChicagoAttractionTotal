package edu.uic.cs478.Project3II;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle sentOver = intent.getExtras();
        Log.d("KAYLYN","EXTRACTED");
        String check = sentOver.getString("buttonChoice");

        if(check.equals("restaurant")){
            Log.d("Check1", "restaurant");
            Intent intent1 = new Intent(context, RestaurantsActivity.class);

            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
        else if(check.equals("attractions")){
            Log.d("Check2", "attractions");
            System.out.println("ATTRACtioNS");
            Intent intent2 = new Intent(context,AttractionsActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent2);
        }
    }
}