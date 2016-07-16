package com.example.ienning.ncuhome.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.adapter.ItemAdapter;
import com.example.ienning.ncuhome.db.ItemArticle;
import com.example.ienning.ncuhome.ui.MyItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        RecyclerView home_rv = (RecyclerView) findViewById(R.id.home_rv);
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
        home_rv.addItemDecoration(new MyItemDecoration());
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        home_rv.setLayoutManager(layoutManager);
        /*
        home_rv.setOnItemClickListener(new ItemAdapter.OnRecyclerViewItemClickListener(){
            @Override
            public void onItemClick(View view, List<ItemArticle> data) {
                data = dataList;
            }
        });
        */

    }
}
