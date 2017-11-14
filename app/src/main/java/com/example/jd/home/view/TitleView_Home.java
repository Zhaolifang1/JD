package com.example.jd.home.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jd.R;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class TitleView_Home extends RelativeLayout{
    private ImageView fg_homepage_zxingiv;
    private TextView fg_homepage_zxingtv;
    private RelativeLayout title_homepage_left;
    private EditText title_homepage_et;
    private ImageView fg_homepage_searchiv;
    private ImageView title_homepage_assistant;
    private RelativeLayout title_homepage_search;
    private ImageView fg_homepage_msgiv;
    private TextView fg_homepage_msgtv;
    private RelativeLayout title_homepage_right;
    private ImageView zXingIv;
    private TextView zXingTv;
    private ImageView msgIv;
    private TextView msgTv;
    private ImageView searchIv;
    private EditText et;

    public TitleView_Home(Context context) {
        super(context);
        initView(context);
    }

    public TitleView_Home(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TitleView_Home(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context) {
        zXingIv = findViewById(R.id.title_homepage_zxingiv);
        zXingTv = findViewById(R.id.title_homepage_zxingtv);
        msgIv = findViewById(R.id.title_homepage_msgiv);
        msgTv = findViewById(R.id.title_homepage_msgtv);
        searchIv = findViewById(R.id.title_homepage_searchiv);
        et = findViewById(R.id.title_homepage_et);

        LayoutInflater.from(context).inflate(R.layout.title_home, this);
    }
    public void TitlezxingListener(OnClickListener listener) {
        findViewById(R.id.title_homepage_left).setOnClickListener(listener);
    }

    public void TitleMsgListener(OnClickListener listener) {
        findViewById(R.id.title_homepage_right).setOnClickListener(listener);
    }

    public void setSearchListener(OnClickListener listener) {
        findViewById(R.id.title_homepage_et).setOnClickListener(listener);
    }

    public void setAssistantListener(OnClickListener listener) {
        findViewById(R.id.title_homepage_assistant).setOnClickListener(listener);
    }


    public void setImage() {
        zXingIv.setImageResource(R.drawable.as4);
        zXingTv.setTextColor(Color.BLACK);

        msgIv.setImageResource(R.drawable.as2);
        msgTv.setTextColor(Color.BLACK);

        et.setBackgroundColor(Color.GRAY);
        searchIv.setImageResource(R.drawable.as2);
    }

}
