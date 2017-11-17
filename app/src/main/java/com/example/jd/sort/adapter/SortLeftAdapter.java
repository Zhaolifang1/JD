package com.example.jd.sort.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.sort.bean.SortLeftBean;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortLeftAdapter extends BaseAdapter {
    SortLeftBean sortLeftBean;
    Context context;

    public SortLeftAdapter(SortLeftBean sortLeftBean, Context context) {
        this.sortLeftBean = sortLeftBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sortLeftBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return sortLeftBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    /*接口回调*/
    public interface ClickName{
        void Clickname(int cid);
    }
    private ClickName clickName;

    public void setClickName(ClickName clickName) {
        this.clickName = clickName;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.sort_left_item,null);
            holder = new MyHolder();
            holder.tv = view.findViewById(R.id.sort_left_tv);
            view.setTag(holder);
        }else{
            holder = (MyHolder) view.getTag();
        }

        holder.tv.setText(sortLeftBean.getData().get(i).getName());

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickName.Clickname(sortLeftBean.getData().get(i).getCid());
            }
        });

        return view;
    }
    class MyHolder{
        TextView tv;
    }
}
