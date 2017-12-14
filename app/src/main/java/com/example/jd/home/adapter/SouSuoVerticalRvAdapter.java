package com.example.jd.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.SouSuoBean;
import com.example.jd.sort.bean.GoodEvent;
import com.example.jd.sort.view.XiangQing;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SouSuoVerticalRvAdapter extends RecyclerView.Adapter<SouSuoVerticalRvAdapter.ViewHolder>  {
    private Context context;
    private List<SouSuoBean.DataBean> list;

    public SouSuoVerticalRvAdapter(Context context, List<SouSuoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.sousuoverticalrvitem, null);
        ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] s = images.split("\\|");

        Glide.with(context).load(s[0]).into(holder.img);
        holder.name.setText(list.get(position).getTitle());
        holder.title.setText("￥："+list.get(position).getPrice()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new GoodEvent(list.get(position).pid+""));
                Intent intent = new Intent(context, XiangQing.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView name;
        private TextView title;
        public ViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgaa);
            name= itemView.findViewById(R.id.nameaa);
              title= itemView.findViewById(R.id.titleaa);
        }
}}
