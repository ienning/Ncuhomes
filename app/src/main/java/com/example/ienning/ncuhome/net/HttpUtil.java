package com.example.ienning.ncuhome.net;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by ienning on 16-7-26.
 */
public class HttpUtil {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ReLoginIntercetor()).build();
    public void post(String url, String json, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public void get(String url, String token, Callback callback) {
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new ReLoginIntercetor()).build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "passport " + token)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void postl(String url, String json, String token, Callback callback) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "passport " + token)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
