package com.example.ienning.ncuhome.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.net.HttpUtil;
import com.example.ienning.ncuhome.net.JsonAnalysis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ienning on 16-7-16.
 */
public class FourAndSix extends Activity{
    private LinearLayout backMain;
    private EditText fourUsername;
    private EditText fourPassword;
    private Button fourSave;
    private String fourUsernames;
    private String fourPasswords;
    private String json;
    private String responseStr;
    private int status;
    JsonAnalysis jsonAnalysis;
    SharedPreferences sharedPreferences;
    private TextView centerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fourlogin);
        backMain = (LinearLayout) findViewById(R.id.back_main3);
        fourUsername = (EditText) findViewById(R.id.four_get_username);
        fourPassword = (EditText) findViewById(R.id.four_get_password);
        fourSave = (Button) findViewById(R.id.four_save);
        centerTitle = (TextView) findViewById(R.id.center_title);
        centerTitle.setText("四六级查询");
        jsonAnalysis = new JsonAnalysis();
        backMain.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(FourAndSix.this, "This is test", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FourAndSix.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
        fourSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fourUsernames = fourUsername.getText().toString();
                fourPasswords = fourPassword.getText().toString();
                if(checkFour(fourUsernames, fourPasswords)) {
                    login(fourUsernames, fourPasswords);
                }
            }
        });
    }
    public boolean checkFour(String username, String password) {
        if (username.length() == 0) {
            Toast.makeText(FourAndSix.this, "请输入准考证号", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() == 0) {
            Toast.makeText(FourAndSix.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    public void login(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            json = jsonObject.toString();
            sharedPreferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.postl("http://www.ncuos.com/api/info/cet", json, sharedPreferences.getString("token", null), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(FourAndSix.this, "没有网络链接请求", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        responseStr = response.body().string();
                        Log.i("Ienning", "onResponse: is the responseStr" + responseStr);
                        status = jsonAnalysis.parseJSONWithGsonStatus(responseStr);
                        if (status == 1) {
                            Intent intent = new Intent(FourAndSix.this, FourAndSixInfo.class);
                            startActivity(intent);
                        } else {
                        }
                    }
                    else {
                        FourAndSix.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(FourAndSix.this, "抱歉系统出错了", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
