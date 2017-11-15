package com.example.jd.api;

import com.example.jd.home.bean.SYBean;
import com.example.jd.mine.bean.LoginBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface ApiService {
    @GET("newlist?page=3&size=20")
    rx.Observable<SYBean> getDat();
    //登陆
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> getLogin(@Field("mobile") String mobile, @Field("password") String password);
    //注册
    @FormUrlEncoded
    @POST("user/reg")
    Observable<LoginBean> getRegister(@Field("mobile") String mobile, @Field("password") String password);

}
