package com.example.jd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jd.found.FoundActivity;
import com.example.jd.home.view.HomeFragment;
import com.example.jd.home.view.NoScrollViewPager;
import com.example.jd.mine.view.MyFragment;
import com.example.jd.shop.view.ShopFragment;
import com.example.jd.sort.view.SortFragment;

import java.util.ArrayList;
import java.util.List;

import static com.example.jd.R.id.noscrollViewPager;

public class MainActivity extends AppCompatActivity {

    private static ViewPager viewPager;

    private List<Fragment> list;
    private static RadioGroup radiogroup;
    private static VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
        initView();
        ClickListener(); 
        vpAdapter = new VpAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vpAdapter);
    }
    //viewpager适配器
    public class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }
    //初始化
    private void initView() {
        viewPager = (NoScrollViewPager) findViewById(noscrollViewPager);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SortFragment());
        list.add(new FoundActivity());
        list.add(new ShopFragment());
        list.add(new MyFragment());
    }
    private void ClickListener() {
        //设置默认第一个页面选中
        for (int i = 0; i < list.size(); i++) {
            RadioButton radioButton = (RadioButton) radiogroup.getChildAt(i);
            if (i == 0) {
                viewPager.setCurrentItem(0);
                radioButton.setChecked(true);
            } else {
                radioButton.setChecked(false);
            }
        }
        //点击切换页面
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                for (int i = 0; i < list.size(); i++) {
                    RadioButton button = (RadioButton) radiogroup.getChildAt(i);
                    if (button.isChecked())
                        viewPager.setCurrentItem(i, false);
                }
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        int tosome = getIntent().getIntExtra("to_shop",0);
    viewPager.setCurrentItem(tosome);}
}
