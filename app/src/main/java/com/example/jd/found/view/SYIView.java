package com.example.jd.found.view;


import com.example.jd.found.bean.MoviesBean;

import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface SYIView {
    void showList(List<MoviesBean.RetBean.ListBean> list);
    void showError(String e);
}
