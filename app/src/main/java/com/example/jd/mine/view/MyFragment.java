package com.example.jd.mine.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.jd.R.id.login_register_tv;


/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class MyFragment extends Fragment {

    @Bind(R.id.hander)
    ImageView hander;
    private View view;
    private TextView login;
    private SharedPreferences hander_sp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, null);

        initView();
        //注册EventBus
        EventBus.getDefault().register(this);
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
                .showImageForEmptyUri(R.mipmap.b3h)
                .showImageOnFail(R.mipmap.b3h)

                .displayer(new CircleBitmapDisplayer())
                .build();
//        ImageLoader.getInstance().displayImage(uri, img, build);
        ImageLoader.getInstance().displayImage(uri,img,build);
    }
    //注销EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
    private void initView() {
        login = view.findViewById(login_register_tv);
        hander_sp = getActivity().getSharedPreferences("handerImage", Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }
}
