package com.example.ienning.ncuhome.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.activity.FourAndSix;
import com.example.ienning.ncuhome.db.ItemArticle;

import java.util.List;

/**
 * Created by ienning on 16-7-16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements View.OnClickListener{
    private List<ItemArticle> dataList;
    private static int positons;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public ItemAdapter(Context context, List<ItemArticle> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        try {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
            ItemViewHolder vh = new ItemViewHolder(view);
            view.setOnClickListener(this);
            return vh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        try {
            if (holder instanceof ItemViewHolder) {
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                ItemArticle item = dataList.get(position);
                positons = position;
                itemViewHolder.imageView.setImageResource(item.getSource());
                itemViewHolder.textView.setText(item.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return  dataList.size();
    }

    /*
    @Override
    public int getItemViewTypeItem(int position) {
        return ITEM_VIEW_TYPE_ITEM;
    }
     */

    @Override
    public void onClick(View v) {
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.main_page);
        linearLayout.setBackgroundColor(context.getResources().getColor(R.color.main_grays));
        Intent intent = new Intent(context, FourAndSix.class);
        context.startActivity(intent);
        v.pos
        /*
        switch (getPosition()) {

            case 0:
                Toast.makeText(context, "This is zero", Toast.LENGTH_SHORT).show();
                break;

            case 1:
                Toast.makeText(context, "This is onre", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "This is two", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(context, "This is three", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(context, "This is four", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(context, "This is five", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(context, "This is six", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(context, "This is seven", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(context, "This is eight", Toast.LENGTH_SHORT).show();
                break;
        }
        */

    }
    class ItemViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener, View.OnLongClickListener
    {
        private ImageView imageView;
        private TextView textView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_iv);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, List<ItemArticle> data);
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }
    /*
    public void setposition(int positon) {
        this.positons = positon;
    }
    public int getPositon() {
        return positons;
    }
    */
}
