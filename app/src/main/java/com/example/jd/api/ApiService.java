package com.example.jd.api;

import com.example.jd.found.bean.MoviesBean;
import com.example.jd.found.bean.XQBean;
import com.example.jd.home.bean.SYBean;
import com.example.jd.mine.bean.LoginBean;
import com.example.jd.sort.bean.DetailsBean;
import com.example.jd.sort.bean.FenleiLeftBean;
import com.example.jd.sort.bean.FenleiRightBean;
import com.example.jd.sort.bean.GoodBean;
import com.example.jd.sort.bean.XiangQingBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    //登录
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> getLogin(@Field("mobile") String mobile, @Field("password") String password);
    //注册
    @FormUrlEncoded
    @POST("user/reg")
    Observable<LoginBean> getRegister(@Field("mobile") String mobile, @Field("password") String password);

//    // 分类 左侧接口
//    @GET
//    Observable<SortLeftBean> getSortLeftData(@Url String url);
//
//    // 分类 右侧接口
//    @POST
//    Observable<SortRightBean> getSortRightData(@Url String url, @QueryMap HashMap<String,String> map);
    @GET("product/getCatagory")
    Observable<FenleiLeftBean> getdatas();
    //http://120.27.23.105/product/getProductCatagory
    @GET("product/getProductCatagory")
    Observable<FenleiRightBean> getRightData(@Query("cid") int cid);
    //商品详情
    @FormUrlEncoded
    @POST("product/getProductDetail")
    Observable<XiangQingBean> getGoodsInfo(@Field("pid") String pid,@Field("source") String source);
    //商品列表
    @POST
    Observable<DetailsBean> getDetical(@Url String url, @QueryMap Map<String, Integer> map);
    @POST
    Observable<GoodBean> getgood(@Url String url, @QueryMap Map<String, Integer> map);
    @GET("homePageApi/homePage.do")
    Observable<MoviesBean> getdata();
//    //详情
    @FormUrlEncoded
    @POST("videoDetailApi/videoDetail.do")
    Observable<XQBean> getMovieData(@Field("mediaId") String mediaId);
//    //详情
//    @FormUrlEncoded
//    @POST("front/Commentary/getCommentList.do")
//    Observable<PlBean> getPlData(@Field("mediaId") String mediaId);
}
