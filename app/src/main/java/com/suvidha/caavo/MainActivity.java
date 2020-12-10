package com.suvidha.caavo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    //Room Components Database,Entity(Table),DAO(Commands)
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //To run startSplash() function
        startSplash();
    }

    private void startSplash()
    {
        //Handler is used to run a task after certain amount of time. Here 1500 milliseconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //To start the new activity we pass intent.
                startActivity(new Intent(context,Home.class));
                finish();
            }
        },1500);
    }
}