package com.example.ienning.ncuhome.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.adapter.ItemAdapter;
import com.example.ienning.ncuhome.db.ItemArticle;
import com.example.ienning.ncuhome.personcenter.PersonCenter;
import com.example.ienning.ncuhome.ui.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageButton personCenter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private int state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        personCenter = (ImageButton) findViewById(R.id.person_center);
        RecyclerView home_rv = (RecyclerView) findViewById(R.id.home_rv);
        preferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
        editor = preferences.edit();
        state = preferences.getInt("state", 0);
        //做一个容器，存各种数据的组合
        final List<ItemArticle> dataList = new ArrayList<>();
        dataList.add(new ItemArticle(0, "四六级助手", R.drawable.four_six));
        dataList.add(new ItemArticle(1, "成绩查询", R.drawable.grade));
        dataList.add(new ItemArticle(2, "用电查询", R.drawable.use_elect));
        dataList.add(new ItemArticle(3, "奖学金查询", R.drawable.money));
        dataList.add(new ItemArticle(4,  "课程表 ", R.drawable.time_class));
        dataList.add(new ItemArticle(5, "空余教师查询", R.drawable.empty_class));
        dataList.add(new ItemArticle(6, "失物招领", R.drawable.lost_found));
        dataList.add(new ItemArticle(7, "掌上图书馆", R.drawable.library));
        dataList.add(new ItemArticle(8, "招新专题", R.drawable.student));
        final ItemAdapter itemAdapter = new ItemAdapter(this, dataList);
        home_rv.setAdapter(itemAdapter);

        //画每一块的分割线
        home_rv.addItemDecoration(new MyItemDecoration());

        //建立GradView的列数为3
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        home_rv.setLayoutManager(layoutManager);

        //设置每个Item的监听时间，根据点击时，返回的position来进行不同的处理,回调方法onClick()
        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (state == 1) {
                    switch (position) {
                        case 0:
                            Intent intent = new Intent(MainActivity.this, FourAndSix.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "This is zero", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Intent intent1 = new Intent(MainActivity.this, QueryGrade.class);
                            startActivity(intent1);
                            Toast.makeText(MainActivity.this, "This is onre", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Intent intent2 = new Intent(MainActivity.this, QueryElect.class);
                            startActivity(intent2);
                            Toast.makeText(MainActivity.this, "This is two", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this, "This is three", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            Toast.makeText(MainActivity.this, "This is four", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(MainActivity.this, "This is five", Toast.LENGTH_SHORT).show();
                            break;
                        case 6:
                            Toast.makeText(MainActivity.this, "This is six", Toast.LENGTH_SHORT).show();
                            break;
                        case 7:
                            Toast.makeText(MainActivity.this, "This is seven", Toast.LENGTH_SHORT).show();
                            break;
                        case 8:
                            editor.putInt("state",0);
                            editor.commit();
                            Toast.makeText(MainActivity.this, "This is eight", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            }
        });
        personCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (state == 1) {
                    Intent intent = new Intent(MainActivity.this, PersonCenter.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        //preferences = getSharedPreferences("ncuhome", MODE_PRIVATE);
        setState(preferences.getInt("state", 0));
    }

    public void setState(int state) {
        this.state = state;
    }
}
