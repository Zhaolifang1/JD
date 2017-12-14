package com.example.jd.sort.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.sort.bean.FenleiRightBean;

import java.util.List;


/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class FenleiRightAdapter extends RecyclerView.Adapter<FenleiRightAdapter.ViewHolder> {
    private Context context;
    private List<FenleiRightBean.DataBean> rightlist;

    public FenleiRightAdapter(Context context, List<FenleiRightBean.DataBean> rightlist) {
        this.context = context;
        this.rightlist = rightlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_right_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//      holder.right_name.setText(rightlist.get(position).list.get(0).name);
        holder.right_name.setText(rightlist.get(position).name);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
        holder.childrecycler.setLayoutManager(gridLayoutManager);
        FenleiChildAdapter fenleiChildAdapter = new FenleiChildAdapter(context, rightlist.get(position).list);
        holder.childrecycler.setAdapter(fenleiChildAdapter);
    }

    @Override
    public int getItemCount() {
        return rightlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView right_name;
        public RecyclerView childrecycler;
        public ViewHolder(View itemView) {
            super(itemView);
            right_name=itemView.findViewById(R.id.right_name);
            childrecycler=itemView.findViewById(R.id.childrecycler);
        }
    }
}
