package com.example.jd.sort.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.sort.bean.FenleiLeftBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class FenleileftAdapter extends RecyclerView.Adapter<FenleileftAdapter.ViewHolder> {
    private Context context;
    private List<FenleiLeftBean.DataBean> list;
    private OnClickListener onClickListener;
    public FenleileftAdapter(Context context, List<FenleiLeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    public OnClickListener getOnClickListener(){
       return onClickListener;
    }
    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public interface OnClickListener{
        void OnDianji(View v, int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_left_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      holder.left_name.setText(list.get(position).name);
        holder.left_img.setImageURI(list.get(position).icon);
//        EventBus.getDefault().postSticky(new FenleiEvent(list.get(position).cid));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EventBus.getDefault().postSticky(new FenleiEvent(list.get(position).cid));
                onClickListener.OnDianji(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView left_name;
        public SimpleDraweeView left_img;
        public ViewHolder(View itemView) {
            super(itemView);
            left_name=itemView.findViewById(R.id.left_name);
            left_img=itemView.findViewById(R.id.left_img);
        }
    }
}
