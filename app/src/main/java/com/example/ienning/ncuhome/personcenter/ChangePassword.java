package com.example.ienning.ncuhome.personcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.net.HttpUtil;

/**
 * Created by ienning on 16-8-1.
 */
public class ChangePassword extends Activity{
    private EditText oldPassword;
    private EditText newPassword;
    private String odpassword;
    private String nwpassword;
    private Button changePassword;
    private LinearLayout back;
    private TextView centerTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_change_password);
        oldPassword = (EditText) findViewById(R.id.old_password);
        newPassword = (EditText) findViewById(R.id.new_password);
        changePassword = (Button) findViewById(R.id.to_change_password);
        back = (LinearLayout) findViewById(R.id.back_main3);
        centerTitle = (TextView) findViewById(R.id.center_title);
        centerTitle.setText("修改密码");
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                odpassword = oldPassword.getText().toString();
                nwpassword = newPassword.getText().toString();
                HttpUtil httpUtil = new HttpUtil();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePassword.this, PersonCenter.class);
                startActivity(intent);
            }
        });
    }
}
