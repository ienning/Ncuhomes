package com.example.ienning.ncuhome.activity;

import android.app.Activity;
import android.content.SharedPreferences;
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
import com.example.ienning.ncuhome.adapter.Term;
import com.example.ienning.ncuhome.db.MyDatabaseHelper;
import com.example.ienning.ncuhome.net.HttpUtil;
import com.example.ienning.ncuhome.net.JsonAnalysis;

import java.io.IOException;
import java.util.ArrayList;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_query_grade);
        tvOptions = (LinearLayout) findViewById(R.id.tvOptions);
        tvTime = (TextView) findViewById(R.id.tvTime);
        gradeList = (ListView) findViewById(R.id.query_grade_list);
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
                String term = optionsItems.get(options1).getTerm();
                Log.i("Ienning", "The String is " + term);
                try {
                    HttpUtil httpUtil = new HttpUtil();
                    httpUtil.get("http://www.ncuos.com/api/info/scores?term=" + term, sharedPreferences.getString("token", null), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(QueryGrade.this, "没有网络来链接请求", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            responseStr = response.body().string();
                            Log.i("Ienning", "running is " + response.code() + response.isSuccessful());
                            Log.i("Ienning", "onResponse: " + responseStr);

                        QueryGrade.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("Ienning", "running is " + responseStr);
                                JsonAnalysis jsonAnalysis = new JsonAnalysis();
                                jsonAnalysis.parseJSONWithGsons(QueryGrade.this, responseStr);
                            }
                        });
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
