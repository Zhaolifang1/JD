package com.example.jd.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.home.adapter.MyGridViewAdapter;
import com.example.jd.home.adapter.MyViewPagerAdapter;
import com.example.jd.home.adapter.SYMyAdapter;
import com.example.jd.home.api.Api;
import com.example.jd.home.bean.ProductListBean;
import com.example.jd.home.bean.SYBean;
import com.example.jd.home.presenter.Presenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class HomeFragment extends Fragment implements Iview, ViewPager.OnPageChangeListener{

    private RecyclerView recycler;
    private View view;
    private Banner banner;
    private List<Integer> images = new ArrayList<>();//存放本地图片的ID

    private ViewGroup points;//小圆点指示器
    private ImageView[] ivPoints;//小圆点图片集合
    private ViewPager viewPager;
    private int totalPage;//总的页数
    private int mPageSize = 10;//每页显示的最大数量
    private List<ProductListBean> listDatas;//总的数据源
    private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
    private int currentPage;//当前页

    private int[] picname={R.drawable.vp01,R.drawable.vp02,R.drawable.vp03,R.drawable.vp04,
            R.drawable.vp05,R.drawable.vp06,R.drawable.vp07,R.drawable.vp08,R.drawable.vp09,
            R.drawable.vp10,R.drawable.vp11,R.drawable.vp12,R.drawable.vp13,
            R.drawable.vp14,R.drawable.vp15,R.drawable.vp16,R.drawable.vp17,R.drawable.vp18,
            R.drawable.vp19,R.drawable.vp20,};
    private String[] proName = {"超市","全球购","服装城","生鲜","京东到家","充值",
            "领京豆","领劵","惠赚钱","PLUS会员","物流查询","白条","机票火车票","酒店",
            "旅游","司法拍卖","二手优品","京东智能","沃尔玛","全部"};

    private UPMarqueeView upview1;
    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);
        initView();
        setBanner();
        //模拟数据源
        setDatas();
        setViewPager();
        initParam();
        initdata();
        initViews();
        GridLayoutManager glide = new GridLayoutManager(getContext(),2);
        recycler.setLayoutManager(glide);
        Presenter persenter = new Presenter(this);
        persenter.getok(Api.SY);

        return view;
    }



    /**
     * 实例化控件
     */
    private void initParam() {
        upview1 = view.findViewById(R.id.upView);
    }
    /**
     * 初始化界面程序
     */
    private void initViews() {
        setView();
        upview1.setViews(views);
        /**
         * 设置item_view的监听
         */
        upview1.setOnItemClickListener(new UPMarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(getContext(), "你点击了第几个items" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 =  moreView.findViewById(R.id.tv1);
            TextView tv2 =  moreView.findViewById(R.id.tv2);

            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), position + "你点击了" + data.get(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            /**
             * 设置监听
             */
            moreView.findViewById(R.id.rl2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), position + "你点击了" + data.get(position + 1).toString(), Toast.LENGTH_SHORT).show();
                }
            });
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
            }

            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }
    /**
     * 初始化数据
     */
    private void initdata() {
        data = new ArrayList<>();
        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");
        data.add("iPhone8最感人变化成真，必须买买买买!!!!");
        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");
        data.add("iPhone7价格曝光了！看完感觉我的腰子有点疼...");
        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");
//        data.add("竟不是小米乐视！看水抢了骁龙821首发了！！！");

    }
    private void setViewPager(){
        LayoutInflater inflate= LayoutInflater.from(getContext());
        //总的页数，取整（这里有三种类型：Math.ceil(3.5)=4:向上取整，只要有小数都+1  Math.floor(3.5)=3：向下取整  Math.round(3.5)=4:四舍五入）
        totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
        viewPagerList = new ArrayList<>();
        for(int i=0;i<totalPage;i++){
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflate.inflate(R.layout.vp_grid,viewPager,false);
            gridView.setAdapter(new MyGridViewAdapter(getContext(),listDatas,i,mPageSize));
            //添加item点击监听

            //每一个GridView作为一个View对象添加到ViewPager集合中
            viewPagerList.add(gridView);
        }
        //设置ViewPager适配器
        viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));
        //小圆点指示器
        ivPoints = new ImageView[totalPage];
        for(int i=0;i<ivPoints.length;i++){
            ImageView imageView = new ImageView(getContext());
            //设置图片的宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(10,10));
            if(i == 0){
                imageView.setBackgroundResource(R.drawable.dot_selected);
            }else{
                imageView.setBackgroundResource(R.drawable.dot_unselected);
            }
            ivPoints[i] = imageView;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 20;//设置点点点view的左边距
            layoutParams.rightMargin = 20;//设置点点点view的右边距
            points.addView(imageView,layoutParams);
        }
        //设置ViewPager滑动监听
        viewPager.addOnPageChangeListener(this);
    }
    private void setDatas() {
        listDatas = new ArrayList<>();
        for(int i=0;i<proName.length;i++){
            listDatas.add(new ProductListBean(proName[i], picname[i]));
        }
    }
    //初始化控件
    private void initView(){
        recycler = view. findViewById(R.id.recycler);
        banner =  view.findViewById(R.id.fg_homepage_banner);
        viewPager =  view.findViewById(R.id.viewPager);
        //初始化小圆点指示器
        points =  view.findViewById(R.id.points);

    }


    /**
     * 显示Banner
     */
    private void setBanner() {
        /******显示本地图片********/
        //图片加载器中需要用到的path；
        images.add(R.drawable.shouye01);
        images.add(R.drawable.shouye02);
        images.add(R.drawable.shouye03);
        images.add(R.drawable.shouye04);
        images.add(R.drawable.shouye05);
        images.add(R.drawable.shouye06);
        images.add(R.drawable.shouye07);
        images.add(R.drawable.shouye08);
        //添加本地mipmap下的图片；
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });

        banner.setImages(images);
        banner.start();
    }
    @Override
    public void error(String rr) {

    }

    @Override
    public void showsuccess(List<SYBean.GoodsListBean> list) {
        SYMyAdapter adapter=new SYMyAdapter(getContext(),list);

        recycler.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //改变小圆圈指示器的切换效果
        setImageBackground(position);
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    /**
     * 改变点点点的切换效果
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < ivPoints.length; i++) {
            if (i == selectItems) {
                ivPoints[i].setBackgroundResource(R.drawable.dot_selected);
            } else {
                ivPoints[i].setBackgroundResource(R.drawable.dot_unselected);
            }
        }
    }
}
