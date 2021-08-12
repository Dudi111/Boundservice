package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mbtnplay;
    private Button mbtnpause;
    private Button mbtnstop;
    private MusicService musicService;


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            MusicService.ServiceBinder serviceBinder= (MusicService.ServiceBinder) binder;
            musicService=serviceBinder.getMusicservice();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

        Intent intent=new Intent(this,MusicService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    private void initview() {
        mbtnplay=findViewById(R.id.play);
        mbtnpause=findViewById(R.id.pause);
        mbtnstop=findViewById(R.id.btnstop);
        mbtnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.play();
            }
        });
        mbtnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.pause();
            }
        });
        mbtnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.stop();
            }
        });
    }
}