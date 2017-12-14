package com.example.jd.sort.view;

import com.example.jd.sort.bean.DetailsBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface DetailIView {
    //获取请求好的数据
     void getShowData(List<DetailsBean.DataBean> data);
}
