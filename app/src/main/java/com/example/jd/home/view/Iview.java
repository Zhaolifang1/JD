package com.example.jd.home.view;

import com.example.jd.home.bean.SYBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface Iview {
    void  error(String rr);

    void showsuccess(List<SYBean.GoodsListBean> list);
}
