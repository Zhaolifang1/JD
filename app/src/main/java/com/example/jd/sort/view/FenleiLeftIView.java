package com.example.jd.sort.view;


import com.example.jd.sort.bean.FenleiLeftBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface FenleiLeftIView {
    void showList(List<FenleiLeftBean.DataBean> list);
    void showError(String e);

}
