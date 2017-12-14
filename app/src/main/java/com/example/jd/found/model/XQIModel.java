package com.example.jd.found.model;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface XQIModel {
    //请求网路数据
    void getHttpData(String url, String mediaId, SYOnRequestListener syOnRequestListener);
}
