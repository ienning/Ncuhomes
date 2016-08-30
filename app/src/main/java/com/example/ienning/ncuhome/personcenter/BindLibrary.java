package com.example.ienning.ncuhome.personcenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.net.HttpUtil;

/**
 * Created by ienning on 16-8-1.
 */
public class BindLibrary extends Activity{
    private EditText libraryUsername;
    private EditText libraryPassword;
    private Button libraryBind;
    private String libusername;
    private String libpassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private LinearLayout back;
    private TextView centerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bind_library);
        libraryUsername = (EditText) findViewById(R.id.library_get_username);
        libraryPassword = (EditText) findViewById(R.id.library_get_password);
        libraryBind = (Button) findViewById(R.id.library_bind);
        centerTitle = (TextView) findViewById(R.id.center_title);
        centerTitle.setText("添加图书馆帐号");
        back = (LinearLayout) findViewById(R.id.back_main3);
        sharedPreferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        libraryBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                libusername = libraryUsername.getText().toString();
                libpassword = libraryPassword.getText().toString();
                if (checkLibrary(libusername, libpassword)) {

                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BindLibrary.this, PersonCenter.class);
                startActivity(intent);
            }
        });
    }
    public boolean checkLibrary(String username, String password) {
        if (username.length() == 0)
        {
            Toast.makeText(BindLibrary.this, "请输入学号", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (username.length() != 10)
        {
            Toast.makeText(BindLibrary.this, "请输入正确的学号", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() == 0)
        {
            Toast.makeText(BindLibrary.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    public void login(String username, String password) {
        HttpUtil httpUtil = new HttpUtil();
        
    }
}