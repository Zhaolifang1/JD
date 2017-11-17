package com.example.jd.sort.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.app.MessageEvent;
import com.example.jd.sort.bean.SortRightBean;
import com.example.jd.sort.view.MyGridView;
import com.example.jd.sort.view.XiangQing;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SortRightAdapter extends BaseAdapter {
    // 数据源
    SortRightBean rightBean;
    // 上下文
    Context context;
    ArrayList<SortRightBean.DataBean.ListBean> right_lists;
    // 用于展示无图gridView
    //ArrayList<String> right_lists;

    public SortRightAdapter(SortRightBean rightBean, Context context) {
        this.rightBean = rightBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        System.out.print("右边的长度"+rightBean.data.size());
        return rightBean.data.size();
    }

    @Override
    public Object getItem(int i) {
        return rightBean.data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public interface GetItemData{
        void getItemData(String name,String pscid);
    }
    private GetItemData getItemData;

    public void GetItemData(GetItemData getItemData) {
        this.getItemData = getItemData;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sort_right_item, null);
            holder = new MyHolder();
            holder.tv = view.findViewById(R.id.sort_right_title);
            holder.gridView=view.findViewById(R.id.sort_right_grid);
            holder.gridView=view.findViewById(R.id.sort_right_grid);
            holder.gridView = view.findViewById(R.id.sort_right_grid);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        Log.d("text",rightBean.data.get(i).name);
        holder.tv.setText(rightBean.data.get(i).name);

        right_lists = new ArrayList<>();

        for (int j = 0; j < rightBean.data.get(i).list.size(); j++) {
            List<SortRightBean.DataBean.ListBean> list = rightBean.data.get(i).list;
            SortRightBean.DataBean.ListBean listBean = list.get(j);
//            right_lists.add(new SortRightBean.DataBean.ListBean(listBean.icon, listBean.name, listBean.pcid, listBean.pscid));
            right_lists.add(new SortRightBean.DataBean.ListBean(listBean.icon,listBean.name,listBean.pcid,listBean.pscid));
        }
        // 无图的gridview
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,right_lists);

        /*有图的gridview*/
        final RightItemAdapter adapter = new RightItemAdapter(right_lists, context);
        holder.gridView.setAdapter(adapter);

        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EventBus.getDefault().postSticky(new MessageEvent(1+i+""));
//                getItemData.getItemData(right_lists.get(i).name,right_lists.get(i).pscid+"");
                Intent intent = new Intent(context, XiangQing.class);
                context.startActivity(intent);
            }
        });

        return view;
    }

    class MyHolder {
        TextView tv;
        MyGridView gridView;
    }
}
