package com.example.jd.sort.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.sort.bean.GoodBean;
import com.example.jd.sort.bean.GoodEvent;
import com.example.jd.sort.view.XiangQing;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.MyViewHolder> {


     List<GoodBean.DataBean> list;
     Context context;

    public GoodAdapter(List<GoodBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goodlistitem, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GoodBean.DataBean dataBean = list.get(position);
        holder.goodlistTvtitle.setText(dataBean.getTitle());
        String images = dataBean.getImages();
        if(images!=null){


        String[] split = images.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.goodlistImg.setImageURI(uri);
        holder.goodlistTvprice.setText("￥ "+dataBean.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new GoodEvent(dataBean.getPid()+""));
                Intent in = new Intent(context, XiangQing.class);

                context.startActivity(in);
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


     class MyViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.goodlist_img)
        SimpleDraweeView goodlistImg;
//        @BindView(R.id.goodlist_tvtitle)
        TextView goodlistTvtitle;
//        @BindView(R.id.goodlist_tvprice)
        TextView goodlistTvprice;
        public MyViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            goodlistImg=itemView.findViewById(R.id.goodlist_img);
            goodlistTvtitle=itemView.findViewById(R.id.goodlist_tvtitle);
            goodlistTvprice=itemView.findViewById(R.id.goodlist_tvprice);
        }
    }
}
