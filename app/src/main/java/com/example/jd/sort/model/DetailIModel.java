package com.example.jd.sort.model;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface DetailIModel {
    //请求网路数据
    void getHttpData(String url, int pscid,int page, int sort,OnDetailListener onDetailListener);
}
