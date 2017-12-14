package com.example.jd.home.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jd.R;
import com.example.jd.SouSuoBean;
import com.example.jd.home.adapter.SouSuoVerticalRvAdapter;
import com.example.jd.home.sousuo.IViewaa;
import com.example.jd.home.sousuo.Presaenter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class SouSuoActivity extends AppCompatActivity implements IViewaa{

    private List<SouSuoBean.DataBean> list=new ArrayList<>();
    private static final String TAG = "SouSuoActivity";
    private RecyclerView recy;
    private SouSuoVerticalRvAdapter souSuoVerticalRvAdapter;
    private Button sousuo;
    private EditText et;
    private String a="月饼";
    private Presaenter presaenter;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);

        back = (Button) findViewById(R.id.ssfinish);
        recy = (RecyclerView)findViewById(R.id.recy);
        sousuo = (Button) findViewById(R.id.sousuo);
        et = (EditText)findViewById(R.id.et);

        presaenter = new Presaenter();
        presaenter.attachView(this);
        jiexi(a);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                String trim = et.getText().toString().trim();
                if (trim!=null){
                    jiexi(trim);
                    souSuoVerticalRvAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void jiexi(String a) {
        presaenter.getNews(a);
    }


    @Override
    public void success(List<SouSuoBean.DataBean> news) {
        if (news!=null){
            list.addAll(news);
            Log.i(TAG,"aaaaa"+list.size());
            souSuoVerticalRvAdapter = new SouSuoVerticalRvAdapter(SouSuoActivity.this, list);
            recy.setAdapter(souSuoVerticalRvAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,VERTICAL,false );
            recy.setLayoutManager(linearLayoutManager);
            souSuoVerticalRvAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void failes(Exception o) {

    }
}
