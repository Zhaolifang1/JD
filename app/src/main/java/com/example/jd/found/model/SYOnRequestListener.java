package com.example.jd.found.model;


import com.example.jd.found.bean.MoviesBean;
import com.example.jd.found.bean.XQBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface SYOnRequestListener {
    void OnSuccess(List<MoviesBean.RetBean.ListBean> list);
    void OnError(String e);
    void OnIntoData(XQBean xqBean);
}
