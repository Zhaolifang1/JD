package com.example.jd.mine.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jd.R;
import com.example.jd.mine.presenter.MyPresenter;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends AppCompatActivity implements MyIView {
    private SharedPreferences sp;
    public static final String TAG = "LoginActivity";
    @Bind(R.id.close)
    ImageView close;
    //账号
    @Bind(R.id.username)
    EditText name;
    //密码
    @Bind(R.id.userpwd)
    EditText pwd;

    //手机注册
    @Bind(R.id.mobile_register)
    TextView mobile;
    //忘记密码
    @Bind(R.id.forget_pwd)
    TextView forgetPwd;
    ////普通注册
    @Bind(R.id.putongzhuce)
    TextView putongzhuce;
    //QQ登录
    @Bind(R.id.QQ)
    ImageView QQ;
    @Bind(R.id.btnlogin)
    Button btnlogin;
    private MyPresenter myPresenter;
    private int b=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);

    }


    @OnClick({R.id.close,  R.id.mobile_register, R.id.forget_pwd, R.id.putongzhuce, R.id.QQ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close://左上角的关闭页面按钮
                finish();
                break;

            case R.id.mobile_register://手机注册
                Mobile_ByMob_Register();
                break;
            case R.id.forget_pwd://忘记密码
                break;
            case R.id.putongzhuce://普通注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.QQ:
               QQLogin();
                break;
        }
    }
    public void QQLogin(){
        UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                String iconurl = map.get("iconurl");
                String name = map.get("name");
                //使用EventBus把值传给我的
                EventBus.getDefault().post(new FirstEvent(iconurl, name));
                finish();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    //通过MOb注册手机
    private void Mobile_ByMob_Register() {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                }
            }
        });
        registerPage.show(LoginActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onLoginSuccess(String code) {
        if (code.equals("0")) {
            Toast.makeText(LoginActivity.this, "登陆成功!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onLoginSuccess: ===========" + code);
            finish();
            String s = code.toString();
            Shuju shuju = new Shuju(s,b);
            EventBus.getDefault().postSticky(shuju);
        }
    }

    @Override
    public void onLoginFailed(String error) {
        if (error.equals("1")) {
            Toast.makeText(LoginActivity.this, "登陆失败!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onLoginFailed: ===========" + error);
        }
    }

    @OnClick(R.id.btnlogin)
    public void onViewClicked() {
//        Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
        //登录按钮
                String loginname = name.getText().toString().trim();
                String loginpwd = pwd.getText().toString().trim();
                Log.d("loginname", loginname + "  " + loginpwd);

                if ((loginname.equals("") || loginname == null) && (loginpwd.equals("") || loginpwd == null)) {
                    Toast.makeText(LoginActivity.this, "手机号，密码不能为空!", Toast.LENGTH_SHORT).show();
                } else {
                    myPresenter.setLoginInfo(loginname, loginpwd);
                }
    }
}
