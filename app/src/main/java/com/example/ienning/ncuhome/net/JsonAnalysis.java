package com.example.ienning.ncuhome.net;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ienning.ncuhome.activity.MyApplication;
import com.example.ienning.ncuhome.db.MyDatabaseHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ienning on 16-7-27.
 */
public class JsonAnalysis {
    private String token;
    private MyDatabaseHelper myDatabaseHelper;
    private Context mContext;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    public String parseJSONWithGson(String json) {
        Jsondb jsondb = gson.fromJson(json, Jsondb.class);
        return jsondb.getToken();
    }
    public void parseJSONWithGsonSed(String json) {
        Jsondb jsondb  = gson.fromJson(json, Jsondb.class);
    }
    public int parseJSONWithGsonStatus(String json) {
        Jsondb jsondb = gson.fromJson(json, Jsondb.class);
        return jsondb.getStatus();
    }
    public JsonAnalysis() {

    }
    public JsonAnalysis(Context context) {
        mContext = context;
    }
    public String parseJSONWithScores(String json) {
        return "";
    }
    public void parseJSONWithGsons(Context context, String json) {
        Type userListType = new TypeToken<UserGrade<List<List<UserScores>>>>(){}.getType();
        UserGrade<List<List<UserScores>>> userListGrade = gson.fromJson(json, userListType);
        List<List<UserScores>> scores = userListGrade.scores;
        Iterator<UserScores> it = scores.get(0).iterator();
        myDatabaseHelper = new MyDatabaseHelper(context, "QueryGrade.db", null, 1);
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        while (it.hasNext()) {
            UserScores us = it.next();
            Log.i("Ienning", "lesson The result is " + us.credit + " "+ us.lesson_name + " " + us.score + " " + us.term);
            values.put("term", us.term);
            values.put("lessonName", us.lesson_name);
            values.put("score", us.score);
            values.put("credit", us.credit);
            db.insert("Grade", null, values);
            values.clear();
        }
        //Log.i("Ienning", "print the result is" + scores.get(0).s);
    }
    public void parseJSONWithMyElect(String json) {
        Log.i("Ienning", "parse de json is " + json);
        UserElectric userElectric= gson.fromJson(json, UserElectric.class);
        UserMyElectric userMyElectric = userElectric.data;
        mContext = MyApplication.getContext();
        preferences = mContext.getSharedPreferences("ncuhome", mContext.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("electUserNumber", userMyElectric.byyd);
        editor.putString("electLeftMoney", userMyElectric.xjye);
        editor.putString("electLeftNumber", userMyElectric.dlye);
        editor.putString("electExceptTime", userMyElectric.syts);
        editor.commit();
        //parseJSONWithElect(userMyElectric);
    }
    public void parseJSONWithFour(String json) {
        UserFourAndSix userFourAndSix = gson.fromJson(json, UserFourAndSix.class);
        UserMyFourAndSix userMyFourAndSix = userFourAndSix.data;
        mContext = MyApplication.getContext();
        preferences = mContext.getSharedPreferences("ncuhome", mContext.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("fourUserName", userMyFourAndSix.name);
        editor.putString("fourSchool", userMyFourAndSix.scholl);
        editor.putString("fourType", userMyFourAndSix.type);
        editor.putString("fourExamid", userMyFourAndSix.examid);
        editor.putString("fourTime", userMyFourAndSix.time);
        editor.putString("fourTotal", userMyFourAndSix.total);
        editor.putString("fourListening", userMyFourAndSix.listening);
        editor.putString("fourReading", userMyFourAndSix.reading);
        editor.putString("fourWritingTranslating", userMyFourAndSix.writing_translating);
        editor.commit();
        /*
        //Type userElectListType = new TypeToken<UserElectric<List<UserMyElectric>>>(){}.getType();
        //UserElectric<List<UserMyElectric>> userElectric = gson.fromJson(json, userElectListType);
        List<UserMyElectric> electrics = userElectric.data;
        Iterator<UserMyElectric> it = electrics.iterator();
        if (it.hasNext()) {
            UserMyElectric electricsResult = it.next();
            Log.i("Ienning", "parseJSONWithElect: " + electricsResult.byyd);
        }
        */
    }
}
