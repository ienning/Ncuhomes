package com.example.ienning.ncuhome.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by ienning on 16-7-17.
 */
public class Login extends Activity{

    private EditText username;
    private EditText password;
    private String user;
    private String passwd;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private Button tologin;
    private String token;
    private String responseStr;
    private String json;
    private int status;
    JsonAnalysis jsonAnaly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        tologin = (Button) findViewById(R.id.tologin);
        jsonAnaly = new JsonAnalysis();
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                passwd = password.getText().toString();
                if (checkLogin(user, passwd))
                {
                    login(user, passwd);
                }
            }
        });
    }
    public boolean checkLogin(String username, String password)
    {
        if (username.length() == 0)
        {
            Toast.makeText(Login.this, "请输入学号", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (username.length() != 10)
        {
            Toast.makeText(Login.this, "请输入正确的学号", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() == 0)
        {
            Toast.makeText(Login.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    public void login(String username, String password)
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
            json = jsonObject.toString();
            HttpUtil httpUtil = new HttpUtil();
            Log.i("Ienning", "The test is " + jsonObject.toString());
            httpUtil.post("http://www.ncuos.com/api/user/token", json, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(Login.this, "没有网络链接请求!", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    responseStr = response.body().string();
                    Log.i("Ienning", "response de jie guo " + responseStr);
                    status = jsonAnaly.parseJSONWithGsonStatus(responseStr);
                    Log.i("Ienning", "response code is " + response.code() + response.isSuccessful());
                    if (status == 1)
                    {
                        Login.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                token = jsonAnaly.parseJSONWithGson(responseStr);
                                preferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
                                editor = preferences.edit();
                                Log.i("Ienning", "LoginSecussfully" + token);
                                editor.putInt("state", 1);
                                editor.putString("username", user);
                                editor.putString("password", passwd);
                                editor.putString("token", "isdnsaiodssss");
                                editor.putString("userJson", json);
                                editor.commit();
                                Log.i("Ienning", "This test is " + preferences.getString("userJson", null));
                                Log.i("Ienning", "token is " + preferences.getString("token", null));
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                    else {
                        Login.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
        });
        } catch (JSONException e1)
        {
            e1.printStackTrace();
        } catch (Exception e2)
        {
            e2.printStackTrace();
        }
    }
}
