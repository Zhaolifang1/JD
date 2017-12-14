package com.example.jd.sort.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.sort.bean.DetialEvent;
import com.example.jd.sort.bean.FenleiRightBean;
import com.example.jd.sort.view.GoodActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class FenleiChildAdapter extends RecyclerView.Adapter<FenleiChildAdapter.ViewHolder> {
    private Context context;
    private List<FenleiRightBean.DataBean.ListBean> listBean;

    public FenleiChildAdapter(Context context, List<FenleiRightBean.DataBean.ListBean> listBean) {
        this.context = context;
        this.listBean = listBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_right_child, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      holder.childimg.setImageURI(listBean.get(position).icon);
        holder.childname.setText(listBean.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new DetialEvent(listBean.get(position).pscid));
                Intent intent = new Intent(context, GoodActivity.class);
//                context.startActivity(intent);
                  context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBean.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public SimpleDraweeView childimg;
        public TextView childname;
        public ViewHolder(View itemView) {
            super(itemView);
            childimg=itemView.findViewById(R.id.right_child_img);
            childname=itemView.findViewById(R.id.right_child_name);
        }
    }
}
