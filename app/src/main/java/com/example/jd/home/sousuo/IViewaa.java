package com.example.jd.home.sousuo;

import com.example.jd.SouSuoBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface IViewaa {
    void success(List<SouSuoBean.DataBean> news);
    void failes(Exception o);

}
