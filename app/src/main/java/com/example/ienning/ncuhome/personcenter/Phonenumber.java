package com.example.ienning.ncuhome.personcenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.net.HttpUtil;
import com.example.ienning.ncuhome.net.JsonAnalysis;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ienning on 16-8-1.
 */
public class Phonenumber extends Activity{
    private TextView showPhonenumber;
    private LinearLayout back;
    private TextView centerTitle;
    private Button changePhonenumber;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String responseStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_phonenumber);
        showPhonenumber = (TextView) findViewById(R.id.show_phonenumber);
        back = (LinearLayout) findViewById(R.id.back_main3);
        centerTitle = (TextView) findViewById(R.id.center_title);
        changePhonenumber = (Button) findViewById(R.id.change_phonenumber);
        centerTitle.setText("手机号码");
        getPhoneNumber();
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
                Toast.makeText(Phonenumber.this, "请到云家园网页版上更换绑定的手机号码", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getPhoneNumber() {
        HttpUtil httpUtil = new HttpUtil();
        sharedPreferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
        JsonAnalysis jsonAnalysis = new JsonAnalysis();
        httpUtil.get("http://www.ncuos.com/api/user/phone_num", sharedPreferences.getString("token", null), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseStr = response.body().string();
                Log.i("Ienning", "onResponse: phonenum is " + responseStr);
                Phonenumber.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showPhonenumber.setText(responseStr.substring(1, responseStr.length() - 2));
                    }
                });
            }
        });
    }
}
