package com.example.ienning.ncuhome.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.activity.MainActivity;

/**
 * Created by ienning on 16-7-17.
 */
public class StartPage extends Activity{
    private final int START_PAGE_TIME = 1000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartPage.this, MainActivity.class);
                StartPage.this.startActivity(intent);
                StartPage.this.finish();
            }
        }, START_PAGE_TIME);
    }
}
