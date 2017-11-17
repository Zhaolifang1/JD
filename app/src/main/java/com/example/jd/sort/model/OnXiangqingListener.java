package com.example.jd.sort.model;

import com.example.jd.sort.bean.XiangQingBean;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface OnXiangqingListener {
    void OnSuccess(XiangQingBean.DataBean dataBean);
    void OnError(String e);
}
