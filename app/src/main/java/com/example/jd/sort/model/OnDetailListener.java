package com.example.jd.sort.model;

import com.example.jd.sort.bean.DetailsBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface OnDetailListener {
    void OnFinishListener(List<DetailsBean.DataBean> data);
}
