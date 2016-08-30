package com.example.ienning.ncuhome.personcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ienning.ncuhome.R;

/**
 * Created by ienning on 16-8-1.
 */
public class Phonenumber extends Activity{
    private TextView showPhonenumber;
    private LinearLayout back;
    private TextView centerTitle;
    private Button changePhonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phonenumber);
        showPhonenumber = (TextView) findViewById(R.id.show_phonenumber);
        back = (LinearLayout) findViewById(R.id.back_main3);
        centerTitle = (TextView) findViewById(R.id.center_title);
        changePhonenumber = (Button) findViewById(R.id.change_phonenumber);
        centerTitle.setText("手机号码");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Phonenumber.this, PersonCenter.class);
                startActivity(intent);
            }
        });
        changePhonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
