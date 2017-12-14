package com.example.jd.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jd.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class MyFragment extends Fragment {

    @Bind(R.id.hander)
    ImageView hander;


    @Bind(R.id.login_register_tv)
    TextView login;
    @Bind(R.id.set)
    ImageView set;
    private View view;

    private SharedPreferences hander_sp;
    private String a;
    private ImageView img;
    private ImageView my_sz;
    private int b;
    private static final String TAG = "MyFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);

        initView();
        //注册EventBus
        EventBus.getDefault().register(this);
//        img = (ImageView) view.findViewById(R.id.roundImageView);
//
//        my_sz = (ImageView) view.findViewById(R.id.my_sz);


//        hander.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getActivity(),TuiChu.class);
//                startActivity(intent);
//            }
//        });
        //加载默认显示头像
        if (hander_sp.getString("ImageKey", null) != null) {
            setImageByImageLoader(hander_sp.getString("ImageKey", null), hander);
        }
        //加载显示名称
        if (hander_sp.getString("NameKey", null) != null) {
            login.setText(hander_sp.getString("NameKey", null));
        }
        ButterKnife.bind(this, view);
        return view;
    }

    //接收适配器传过来的值
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Shuju event) {
        a = event.getA();
        b = event.getB();
        Log.i(TAG, "asasas" + b);
        login.setText("18701295759");

        if (a != null) {
            hander.setBackgroundResource(R.drawable.aaa);
        }


    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TuiChuaa event) {
        String aaa = event.getName();
        if (aaa != null) {
            login.setText(aaa);
            hander.setBackgroundResource(R.mipmap.b3h);
        }
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        hander_sp.edit().putString("ImageKey", event.getImg()).commit();
        hander_sp.edit().putString("NameKey", event.getName()).commit();
        if (hander_sp.getString("ImageKey", null) != null) {
            setImageByImageLoader(hander_sp.getString("ImageKey", null), hander);
        }
        if (hander_sp.getString("NameKey", null) != null) {
            login.setText(hander_sp.getString("NameKey", null));
        }
    }

    //imageLoader 加载图片
    public static void setImageByImageLoader(String uri, ImageView img) {
        DisplayImageOptions build = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .displayer(new CircleBitmapDisplayer())
                .build();
        ImageLoader.getInstance().displayImage(uri, img, build);
    }

    //注销EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    private void initView() {
        hander_sp = getActivity().getSharedPreferences("handerImage", Context.MODE_PRIVATE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();


    }


    @OnClick({R.id.login_register_tv, R.id.set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_register_tv:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.set:
                startActivity(new Intent(getActivity(),SetActivity.class));
//                Intent intent = new Intent(getActivity(), TuiChu.class);
//                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.hander)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), TuiChu.class);
        startActivity(intent);
    }
}
