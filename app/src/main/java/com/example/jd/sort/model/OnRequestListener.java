package com.example.jd.sort.model;


import com.example.jd.sort.bean.FenleiLeftBean;
import com.example.jd.sort.bean.FenleiRightBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface OnRequestListener {
    void OnSuccess(List<FenleiLeftBean.DataBean> list);
    void OnRightSuccess(List<FenleiRightBean.DataBean> list);
    void OnError(String e);
}
