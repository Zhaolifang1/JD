package com.example.jd.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.home.bean.SYBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SYMyAdapter extends RecyclerView.Adapter<SYMyAdapter.ViewHolder> {
    private Context context;
   private List<SYBean.GoodsListBean> list = new ArrayList<>();

    public SYMyAdapter(Context context, List<SYBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public SYMyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shouye_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SYMyAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).image_url).into(holder.sy_img);
        holder.sy_title.setText(list.get(position).goods_name);
        holder.sy_price.setText("¥"+list.get(position).normal_price);
        holder.sy_num.setText(list.get(position).quantity+"人付款");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView sy_img;
        public TextView sy_title;
        public TextView sy_price;
        public TextView sy_num;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.sy_img =  rootView.findViewById(R.id.sy_img);
            this.sy_title =  rootView.findViewById(R.id.sy_title);
            this.sy_price =  rootView.findViewById(R.id.sy_price);
            this.sy_num =  rootView.findViewById(R.id.sy_num);
        }

    }
}
