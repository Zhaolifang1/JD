package com.example.jd.shop.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.shop.adapter.CartAdapter;
import com.example.jd.shop.bean.CarEntity;
import com.example.jd.shop.bean.ChildBean;
import com.example.jd.shop.bean.ParentBean;
import com.example.jd.shop.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class ShopFragment extends Fragment implements View.OnClickListener,ICartView{


    private View view;

    private PopupWindow mPopWindow;
    private ExpandableListView expandableListView;
    private CartAdapter adapter;
    private List<ParentBean> parentList;
    private List<List<ChildBean>> childList;
    private CheckBox gouwuche_footer_check;
    private TextView gouwuche_footer_jiesuan;
    private TextView gouwuche_footer_price;
    private TextView gouwuche_footer_heji;
    private HttpUtils utils;
    private TextView gouwuche_tv;
    private SharedPreferences sp;
    private int sum=0;//总价
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gouwuche, null);

        initView(view);
        initData();
        createEvent();
        zhifu();
        return view;
    }
    private void zhifu() {
        gouwuche_footer_jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp=getContext().getSharedPreferences("User",Context.MODE_PRIVATE);
                boolean islog = sp.getBoolean("islog", true);
                if(islog){
                    if(sum==0){
                        Toast.makeText(getActivity(),"请选择商品",Toast.LENGTH_LONG).show();
                    }else{
                        showPopupWindow();
                    }
                }else{
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initData() {
        final SharedPreferences sp=getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
//        if(sp.getBoolean("islog",false))
        if(sp.getBoolean("islog",true))
        {
            gouwuche_tv.setVisibility(View.GONE);
            expandableListView.setVisibility(View.VISIBLE);
            //调用P层获取数据
            String uid=sp.getString("uid","160");
            HashMap<String,String> params=new HashMap<>();
            params.put("uid",uid);
            utils=new HttpUtils();
            utils.postMap("http://120.27.23.105/product/getCarts", params, new HttpUtils.HttpCallBack() {
                @Override
                public void onSusscess(String data) {
                    Gson gson=new Gson();
                    CarEntity carEntity=gson.fromJson(data,CarEntity.class);
                    parentList = new ArrayList<>();
                    childList = new ArrayList<>();
                    //获取数据
                    List<CarEntity.DataBean> dataBeen = carEntity.getData();
                    for (int i=0;i<dataBeen.size();i++)
                    {
                        ParentBean parentBean=new ParentBean(dataBeen.get(i).getSellerName(),false,true);
                        parentList.add(parentBean);

                        List<ChildBean> childBeen=new ArrayList<ChildBean>();
                        List<CarEntity.DataBean.ListBean> listBeen = dataBeen.get(i).getList();
                        for(int j=0;j<listBeen.size();j++)
                        {
                            CarEntity.DataBean.ListBean listBean=listBeen.get(j);
                            ChildBean bean=new ChildBean(sp.getString("uid",""),listBean.getPid()+"",listBean.getTitle(),listBean.getNum(),false,(int)listBean.getPrice(),true,listBean.getImages().split("\\|")[0]);
                            childBeen.add(bean);
                        }
                        childList.add(childBeen);
                    }

                    adapter = new CartAdapter(getContext(), parentList, childList,ShopFragment.this);
                    expandableListView.setAdapter(adapter);

                    expandableListView.setGroupIndicator(null);
                    for (int i = 0; i < adapter.getGroupCount(); i++) {
                        expandableListView.expandGroup(i);
                    }
                }
            });
        }
        else
        {
            //显示购物车为空
            gouwuche_tv.setVisibility(View.VISIBLE);
            expandableListView.setVisibility(View.GONE);
        }
    }
    private void initView(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.gouwuche_expanded);
        gouwuche_footer_check = (CheckBox) view.findViewById(R.id.gouwuche_footer_check);
        gouwuche_footer_jiesuan = (TextView) view.findViewById(R.id.gouwuche_footer_jiesuan);
        gouwuche_footer_jiesuan.setOnClickListener(this);
        gouwuche_footer_price = (TextView) view.findViewById(R.id.gouwuche_footer_price);
        gouwuche_footer_heji = (TextView) view.findViewById(R.id.gouwuche_footer_heji);
        gouwuche_tv= (TextView) view.findViewById(R.id.gouwuche_tv);

    }

    private void createEvent() {

        //设置选中监听去实现全选
        gouwuche_footer_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true)
                {
                    adapter.allCheck(true);
                }
            }
        });
        //设置点击监听去实现反选
        gouwuche_footer_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取购物车的CheckBox的选中状态
                boolean isCheck=gouwuche_footer_check.isChecked();
                if(!isCheck)
                {
                    adapter.allCheck(false);
                }
            }
        });
    }
    //修改全选按钮的状态
    @Override
    public void changeCheckBtn(boolean flag) {
        gouwuche_footer_check.setChecked(flag);
    }
    //计算商品数量的方法
    @Override
    public void addcount() {
        int count=0;
        for(int i=0;i<adapter.getGroupCount();i++)
        {
            for (int j=0;j<adapter.getChildrenCount(i);j++)
            {
                ChildBean child=adapter.getChild(i,j);
                //如果该对象被选中,则加上这个对象中的价钱
                if(child.isCheck)
                {
                    count+=1;
                }
            }
        }
        gouwuche_footer_jiesuan.setText("结算"+ "("+count+")");
    }
    //计算总价的方法
    @Override
    public void addPrice() {
        //初始化总价
        sum=0;
        //遍历所有的子集合
        for(int i=0;i<adapter.getGroupCount();i++)
        {
            for (int j=0;j<adapter.getChildrenCount(i);j++)
            {
                ChildBean child=adapter.getChild(i,j);
                //如果该对象被选中,则加上这个对象中的价钱
                if(child.isCheck)
                {
                    sum+=child.price*child.saleNum;
                }
            }
        }
        //得到总价,更新UI控件
        gouwuche_footer_price.setText(sum+"");
    }



    @Override
    public void delete(String uid,String pid) {
        //删除的接口回调
        HashMap<String,String> params=new HashMap<>();
        params.put("uid",uid);
        params.put("pid",pid);
        utils.postMap("http://120.27.23.105/product/deleteCart", params, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                parentList.clear();
                childList.clear();
                initData();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.popuplayout, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
        TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);         //显示PopupWindow
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.fragment_gouwuche, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 140, 200);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.pop_computer:{
                adddingdan();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_financial:{
                Intent intent=new Intent(getContext(), PayDemoActivity.class);
                startActivity(intent);
                mPopWindow.dismiss();
            }
        }
    }
    public void adddingdan(){
        HttpUtils utils=new HttpUtils();
        sp=getContext().getSharedPreferences("User", Context.MODE_PRIVATE);
        String uid = sp.getString("uid", "160");
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("price",sum+"");

        utils.postMap("http://120.27.23.105/product/createOrder?", map, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Toast.makeText(getContext(),"创建订单成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
