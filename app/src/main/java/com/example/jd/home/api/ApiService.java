package com.example.jd.home.api;

import com.example.jd.home.bean.SYBean;

import retrofit2.http.GET;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface ApiService {
    @GET("newlist?page=3&size=20")
    rx.Observable<SYBean> getDat();
}
