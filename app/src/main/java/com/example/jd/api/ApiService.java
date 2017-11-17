package com.example.jd.api;

import com.example.jd.home.bean.SYBean;
import com.example.jd.mine.bean.LoginBean;
import com.example.jd.sort.bean.SortLeftBean;
import com.example.jd.sort.bean.SortRightBean;
import com.example.jd.sort.bean.XiangQingBean;

import java.util.HashMap;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
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

    // 分类 左侧接口
    @GET
    Observable<SortLeftBean> getSortLeftData(@Url String url);

    // 分类 右侧接口
    @POST
    Observable<SortRightBean> getSortRightData(@Url String url, @QueryMap HashMap<String,String> map);

    //商品详情
    @FormUrlEncoded
    @POST("product/getProductDetail")
    Observable<XiangQingBean> getGoodsInfo(@Field("pid") String pid);

}
