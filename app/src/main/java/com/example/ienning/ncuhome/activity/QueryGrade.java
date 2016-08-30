package com.example.ienning.ncuhome.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.adapter.ClassScores;
import com.example.ienning.ncuhome.adapter.MyListAdapter;
import com.example.ienning.ncuhome.adapter.Term;
import com.example.ienning.ncuhome.db.MyDatabaseHelper;
import com.example.ienning.ncuhome.net.HttpUtil;
import com.example.ienning.ncuhome.net.JsonAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ienning on 16-8-2.
 */
public class QueryGrade extends Activity{
    private ArrayList<Term> optionsItems = new ArrayList<>();
    private LinearLayout tvOptions;
    TimePickerView pvTime;
    OptionsPickerView pvOptions;
    private TextView tvTime;
    private ListView gradeList;
    SharedPreferences sharedPreferences;
    private String responseStr;
    private MyDatabaseHelper myDatabaseHelper;
    private LinearLayout back;
    private TextView centerTitle;
    private MyDatabaseHelper dbHelper;
    private String term;
    private int status;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_query_grade);
        tvOptions = (LinearLayout) findViewById(R.id.tvOptions);
        tvTime = (TextView) findViewById(R.id.tvTime);
        back = (LinearLayout) findViewById(R.id.back_main3);
        centerTitle = (TextView) findViewById(R.id.center_title);
        sharedPreferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
        myDatabaseHelper = new MyDatabaseHelper(this, "QueryGrade.db", null, 1);
        myDatabaseHelper.getWritableDatabase();
        /*
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);

        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvTime.show();
            }
        });
        */
        pvOptions = new OptionsPickerView(this);

        optionsItems.add(new Term(0, "2013-2014第一学期", "2013-2014-1"));
        optionsItems.add(new Term(1, "2013-2014第二学期", "2013-2014-2"));
        optionsItems.add(new Term(2, "2014-2015第一学期", "2014-2015-1"));
        optionsItems.add(new Term(3, "2014-2015第二学期", "2014-2015-2"));
        optionsItems.add(new Term(4, "2015-2016第一学期", "2015-2016-1"));
        optionsItems.add(new Term(5, "2015-2016第二学期", "2015-2016-2"));
        optionsItems.add(new Term(6, "2016-2017第一学期", "2016-2017-1"));
        optionsItems.add(new Term(7, "2016-2017第二学期", "2016-2017-2"));
        pvOptions.setPicker(optionsItems);
        pvOptions.setCyclic(false);
        pvOptions.setSelectOptions(1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                String tx = optionsItems.get(options1).getPickerViewText();
                tvTime.setText(tx);
                term = optionsItems.get(options1).getTerm();
                Log.i("Ienning", "The String is " + term);
                try {
                    final JsonAnalysis jsonAnalysis = new JsonAnalysis();
                    HttpUtil httpUtil = new HttpUtil();
                    httpUtil.get("http://www.ncuos.com/api/info/scores?term=" + term, sharedPreferences.getString("token", null), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(QueryGrade.this, "没有网络来链接请求", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            responseStr = response.body().string();
                            Log.i("Ienning", "de chu de jie guo shi" + responseStr);
                            //status = jsonAnalysis.parseJSONWithGsonStatus(responseStr);
                            //if (status == 1) {
                                Log.i("Ienning", "running is " + response.code() + response.isSuccessful());
                                Log.i("Ienning", "onResponse: " + responseStr);

                                QueryGrade.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        List<ClassScores> classScoresList = new ArrayList<ClassScores>();
                                        Log.i("Ienning", "running is " + responseStr);
                                        gradeList = (ListView) findViewById(R.id.query_grade_list);
                                        jsonAnalysis.parseJSONWithGsons(QueryGrade.this, responseStr);
                                        initScores(classScoresList);
                                        Iterator<ClassScores> it = classScoresList.iterator();
                                        while (it.hasNext()) {
                                            ClassScores classScore = it.next();
                                            Log.i("Ienning", "run: nenisnfs sd " + classScore.getCredit() + "  " + classScore.getLessonName() + "  " + classScore.getScore());
                                        }
                                        MyListAdapter adapter = new MyListAdapter(QueryGrade.this, R.layout.layout_list_item, classScoresList);
                                        gradeList.setAdapter(adapter);
                                    }
                                });
                            //}
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
        });
        tvOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvOptions.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QueryGrade.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initScores(List<ClassScores> classScoresList) {
        dbHelper = new MyDatabaseHelper(this, "QueryGrade.db", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("Grade", null, "term like ?", new String[]{term + "%"}, null , null, null);
        if (cursor.moveToFirst()) {
            do {
                String lessonName = cursor.getString(cursor.getColumnIndex("lessonName"));
                String score = cursor.getString(cursor.getColumnIndex("score"));
                String credit = cursor.getString(cursor.getColumnIndex("credit"));
                classScoresList.add(new ClassScores(lessonName, score, credit));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    /*
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }
    */
    /*
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvOptions.isShowing()||pvTime.isShowing()) {
                pvOptions.dismiss();
                pvTime.dismiss();
                return true;
            }
            if (pvTime.isShowing()) {
                pvTime.dismiss();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    */
}
