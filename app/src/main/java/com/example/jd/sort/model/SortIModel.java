package com.example.jd.sort.model;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface SortIModel {
    // 请求数据的接口
    void GetLeftData();

    // 请求右侧数据的接口
    void GetRightData(int cid);
}
