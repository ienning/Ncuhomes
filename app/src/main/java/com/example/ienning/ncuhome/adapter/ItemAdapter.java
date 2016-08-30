package com.example.ienning.ncuhome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ienning.ncuhome.R;
import com.example.ienning.ncuhome.db.ItemArticle;

import java.util.List;

/**
 * Created by ienning on 16-7-16.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemArticle> dataList;
    private Context context;
    private OnItemClickListener onRecyclerViewItemClickListener = null;
    public ItemAdapter(Context context, List<ItemArticle> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    //这个是加载ViewHolder，设置视图
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        try {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
            return new ItemViewHolder(view);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //可以在这里面对item进行业务逻辑操作，绑定数据
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        try {
            if (holder instanceof ItemViewHolder) {
                ItemArticle item = dataList.get(position);
                holder.imageView.setImageResource(item.getSource());
                holder.textView.setText(item.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return  dataList.size();
    }

    //在这里设置监听回调
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener // View.OnLongClickListener
    {
        private ImageView imageView;
        private TextView textView;
        private LinearLayout linearLayout;
        public ItemViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_iv);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.main_page);
            linearLayout.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (onRecyclerViewItemClickListener != null) {
                onRecyclerViewItemClickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }
    //设定Item监听接口
    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }
    //暴露一个监听方法给外界调用
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }
}
