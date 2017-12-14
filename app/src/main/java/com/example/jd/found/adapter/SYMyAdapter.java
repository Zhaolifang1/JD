package com.example.jd.found.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jd.R;
import com.example.jd.found.bean.MoviesBean;
import com.example.jd.found.bean.SyMessageEvent;
import com.example.jd.found.view.SecondActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SYMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<MoviesBean.RetBean.ListBean> list;
    private final int BANNER_TYPE = 0,Text_TYPE=2,TITLE_TYPE = 1;

    public SYMyAdapter(Context context, List<MoviesBean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==BANNER_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sy_item01, parent, false);
            return new ViewHolder1(view);
        }
//        if (viewType==Text_TYPE){
//            View view = LayoutInflater.from(context).inflate(R.layout.sy_item03, parent, false);
//            return new ViewHolder3(view);
//        }
        View view = LayoutInflater.from(context).inflate(R.layout.sy_item02, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type){
            case BANNER_TYPE:
                List<String> images=new ArrayList<>();
                for (int i = 0; i <list.size() ; i++) {
                    images.add(list.get(i).childList.get(0).pic);
                }
//                EventBus.getDefault().postSticky(new SyMessageEvent(list.get(position).childList.get(0).dataId,list.get(position).childList.get(0).title));
                ((ViewHolder1)holder).banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                }).setImages(images.subList(0,3))
                        .setDelayTime(1500)
                        .setIndicatorGravity(BannerConfig.CENTER).start();

                break;
            case TITLE_TYPE:
                ((ViewHolder2)holder).img.setImageURI(list.get(position).childList.get(0).pic);
                ((ViewHolder2)holder).title.setText(list.get(position).childList.get(0).title);
//                EventBus.getDefault().postSticky(new FirstEvent(list.get(position).childList.get(0).dataId));

//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        EventBus.getDefault().postSticky(new SyMessageEvent(list.get(position).childList.get(0).dataId,list.get(position).childList.get(0).title));
//                        Intent intent = new Intent(context, SecondActivity.class);
//                        context.startActivity(intent);
//                    }
//                });
 ((ViewHolder2)holder).tiaozhuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EventBus.getDefault().postSticky(new SyMessageEvent(list.get(position).childList.get(0).dataId,list.get(position).childList.get(0).title));
                        Intent intent = new Intent(context, SecondActivity.class);
//                        intent.putExtra("id",list.get(position).childList.get(0).dataId);
//                        intent.putExtra("shareurl",list.get(position).childList.get(0).shareURL);
                        context.startActivity(intent);

                    }
                });
                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return BANNER_TYPE;
        }
//        else if(position==1){
//            return Text_TYPE;
//        }
        return TITLE_TYPE;
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder{

        public Banner banner;
        public RelativeLayout ban;
        public ViewHolder1(View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
            ban=itemView.findViewById(R.id.ban);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{
        public SimpleDraweeView img;
        public TextView title;
        public LinearLayout tiaozhuan;
        public ViewHolder2(View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.item_img);
            title=itemView.findViewById(R.id.title);
            tiaozhuan=itemView.findViewById(R.id.tiaozhuan);
        }
    }
//    public class ViewHolder3 extends RecyclerView.ViewHolder{
//
//        public ViewHolder3(View itemView) {
//            super(itemView);
//        }
//    }
}
