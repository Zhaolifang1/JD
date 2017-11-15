package com.example.jd.mine.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.mine.presenter.RegPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements MyRegIView{

    @Bind(R.id.zhucegouback)
    ImageView zhucegouback;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.userpwd1)
    EditText userpwd1;
    @Bind(R.id.userpwd2)
    EditText userpwd2;
    @Bind(R.id.useremail)
    EditText useremail;
    @Bind(R.id.register)
    Button register;
    private RegPresenter regPresenter;
    private String regname;
    private String regpwd;
    private String regpwd2;
    private String regemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        regPresenter = new RegPresenter(this);

    }

    @OnClick({R.id.zhucegouback, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhucegouback:
                finish();
                break;
            case R.id.register:
                regname = username.getText().toString().trim();
                regpwd = userpwd1.getText().toString().trim();
                regpwd2 = userpwd2.getText().toString().trim();
                regemail = useremail.getText().toString().trim();
                if ((regname.equals("") || regname == null) && (regpwd.equals("") || regpwd == null) &&
                        (regpwd2.equals("") || regpwd2 == null) && (regemail.equals("") || regemail == null)&&regpwd.equals(regpwd2)) {

//                    Toast.error(this, "注册信息不能为空!"+regNicknameStr+regRePassStr, Toast.LENGTH_LONG).show();
                    Toast.makeText(RegisterActivity.this,"注册信息不能为空!",Toast.LENGTH_SHORT).show();
                }else{

                    regPresenter.setRegInfo(regname, regpwd);
                }
                break;
        }
    }

    @Override
    public void onRegSuccess(String code) {
        if (code.equals("0")){

            Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();

            finish();
        }
    }

    @Override
    public void onRegFailed(String error) {
        if (error.equals("1")) {
            Toast.makeText(RegisterActivity.this,"注册失败！",Toast.LENGTH_SHORT).show();
        }
    }
}
