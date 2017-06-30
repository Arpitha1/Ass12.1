package com.example.abhi.broadreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        percent= (TextView)findViewById(R.id.tv);
        getPercentage();
    }

    private void getPercentage() {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                context.unregisterReceiver(this);
                int currentlevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int level =1;
                if(currentlevel>0&&scale>0){
                    level = (currentlevel * 100) / scale;
                }
                percent.setText("Battery level remaining: "+ level + " %");


            }
        };
        IntentFilter batterylevel  = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver,batterylevel);
    }
}
