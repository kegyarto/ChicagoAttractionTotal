package edu.uic.cs478.Project3I;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String PERMISSION_CHECK = "edu.uic.cs478.sp2020.project3" ;
    private static int choice = 0;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button attractions = (Button) findViewById(R.id.attractions);
        Button restaurants =  (Button) findViewById(R.id.restaurants);
        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(1);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(2);
            }
        });
    }

    private void requestPermission(int buttonPressed) {
        if(buttonPressed == 1){
            choice = 1;
            if(ContextCompat.checkSelfPermission(this,PERMISSION_CHECK)==PackageManager.PERMISSION_GRANTED){
                Intent send = new Intent("edu.uic.cs478.sp2020.project3.receiver1");
                send.putExtra("buttonChoice","attractions");
                sendBroadcast(send, PERMISSION_CHECK);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{PERMISSION_CHECK},0);
            }
        }
        else if(buttonPressed == 2){
            choice = 2;
            if(ContextCompat.checkSelfPermission(this,PERMISSION_CHECK)==PackageManager.PERMISSION_GRANTED){
                Intent send = new Intent("edu.uic.cs478.sp2020.project3.receiver2");
                send.putExtra("buttonChoice", "restaurant");
                sendBroadcast(send,PERMISSION_CHECK);
            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{PERMISSION_CHECK},0);
            }
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results){
        if(results.length > 0){
            if(results[0]==PackageManager.PERMISSION_GRANTED){
                if(choice ==1){
                    Intent send = new Intent("edu.uic.cs478.sp2020.project3.receiver1");
                    send.putExtra("buttonChoice","attractions");
                    Toast.makeText(this,"PERMISSION IS GRANTED TO ATTRACTIONS",Toast.LENGTH_SHORT).show();
                    sendBroadcast(send, PERMISSION_CHECK);
                }
                else if(choice == 2){
                    Intent send = new Intent("edu.uic.cs478.sp2020.project3.receiver2");
                    send.putExtra("buttonChoice", "restaurant");
                    Toast.makeText(this,"PERMISSION IS GRANTED TO RESTAURANTS",Toast.LENGTH_LONG).show();
                    sendBroadcast(send,PERMISSION_CHECK);
                }
            }
            else{
                Toast.makeText(this,"PERMISSION IS DENIED",Toast.LENGTH_LONG).show();
            }
        }
    }
}