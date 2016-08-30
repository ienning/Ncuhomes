package com.example.ienning.ncuhome.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ienning.ncuhome.R;

import java.util.List;

/**
 * Created by ienning on 16-8-1.
 */
public class MyListAdapter extends ArrayAdapter<ClassScores>{
    private int resourceId;
    public MyListAdapter(Context context, int textViewResourceId, List<ClassScores> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassScores classScores = getItem(position);
        View view;
        ViewHolders viewHolders;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolders = new ViewHolders();
            viewHolders.linearLayout = (LinearLayout) view.findViewById(R.id.list_item_linear);
            viewHolders.lessonName = (TextView) view.findViewById(R.id.list_item_name);
            viewHolders.score = (TextView) view.findViewById(R.id.list_item_scores);
            viewHolders.credit = (TextView) view.findViewById(R.id.list_item_credit);
            view.setTag(viewHolders);
        } else {
            view = convertView;
            viewHolders = (ViewHolders) view.getTag();
        }
        if ((position % 2) == 0) {
            viewHolders.linearLayout.setBackgroundColor(Color.parseColor("#EBFDF0"));
        }
        viewHolders.lessonName.setText(classScores.getLessonName());
        viewHolders.score.setText(classScores.getScore());
        viewHolders.credit.setText(classScores.getCredit());
        return view;
    }
    class ViewHolders {
        LinearLayout linearLayout;
        TextView lessonName;
        TextView score;
        TextView credit;
    }
}
