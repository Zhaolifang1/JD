package com.example.jd.sort.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.sort.bean.SortRightBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


public class RightItemAdapter extends BaseAdapter {
    // 数据源
    ArrayList<SortRightBean.DataBean.ListBean> lists;
    // 上下文
    Context context;

    public RightItemAdapter(ArrayList<SortRightBean.DataBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sort_right_grid_item, null);
            holder = new MyHolder();
            holder.tv = view.findViewById(R.id.sort_right_grid_item_tv);

            holder.img = view.findViewById(R.id.sort_right_grid_item_img);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }

        Uri uri = Uri.parse(lists.get(i).icon);
        holder.img.setImageURI(uri);

        holder.tv.setText(lists.get(i).name);

        return view;
    }

    class MyHolder {
        TextView tv;
        SimpleDraweeView img;
    }
}