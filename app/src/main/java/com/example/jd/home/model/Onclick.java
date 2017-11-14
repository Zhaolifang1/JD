package com.example.jd.home.model;

import com.example.jd.home.bean.SYBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface Onclick {
    void  datasuccess(List<SYBean.GoodsListBean> list);
    void  error(String rr);
}
