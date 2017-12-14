package com.example.jd.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jd.R;
import com.example.jd.WelcomeActivity;

public class LauckActivity extends AppCompatActivity implements PatternLockLayout.OnPatternStateListener{

    private TextView tvInfo;
    private PatternLockLayout lockLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauck);
        tvInfo = (TextView) findViewById(R.id.txt_patternlock_info);
        tvInfo.setText("请绘制图案密码");
        lockLayout = (PatternLockLayout) findViewById(R.id.layout_lock);
        lockLayout.setOnPatternStateListener(this);
    }

    @Override
    public void onFinish(String password, int sizeOfPoints) {
        if(sizeOfPoints<5)
        {
            tvInfo.setText("请连接至少5个点");
            lockLayout.setAllSelectedPointsError();
        }
//        1235789
        else if( !password.equals("1235789") )
        {
            tvInfo.setText("图案密码错误");
            lockLayout.setAllSelectedPointsError();
        }
        else
        {
            tvInfo.setText("图案正确");
            Intent intent = new Intent(LauckActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onReset() {
        tvInfo.setText("请绘制图案密码");
    }
}
