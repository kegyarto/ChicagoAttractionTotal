package edu.uic.cs478.Project3II;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyReceiver mReceiver;

    private static final String PERMISSION_CHECK = "edu.uic.cs478.sp2020.project3" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Chicago tour guide");
        mReceiver =new MyReceiver();





            IntentFilter i = new IntentFilter();
            i.addAction("edu.uic.cs478.sp2020.project3.receiver1");
            i.addAction("edu.uic.cs478.sp2020.project3.receiver2");

                if(ContextCompat.checkSelfPermission(this,PERMISSION_CHECK)== PackageManager.PERMISSION_GRANTED){
                    registerReceiver(new MyReceiver(),i,
                            "edu.uic.cs478.sp2020.project3",null);
                }
                else{
                    ActivityCompat.requestPermissions(this, new String[]{PERMISSION_CHECK},0);
                }





    }
    public void onRequestPermissionsResult(int code, String[] permissions, int[] results){
        IntentFilter i = new IntentFilter();
        i.addAction("edu.uic.cs478.sp2020.project3.receiver1");
        i.addAction("edu.uic.cs478.sp2020.project3.receiver2");
        if(results.length > 0){
            if(results[0]==PackageManager.PERMISSION_GRANTED){
                registerReceiver(new MyReceiver(),i,
                        "edu.uic.cs478.sp2020.project3",null);
                Toast.makeText(this, "PERMISSION IS GRANTED",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this,"PERMISSION IS DENIED",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}

