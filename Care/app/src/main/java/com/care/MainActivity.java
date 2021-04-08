package com.care;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceClient.getSharedInstance(this.getApplicationContext());
        setContentView(R.layout.activity_main);
    }
}