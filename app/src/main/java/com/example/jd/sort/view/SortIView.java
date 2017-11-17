package com.example.jd.sort.view;

import com.example.jd.sort.bean.SortLeftBean;
import com.example.jd.sort.bean.SortRightBean;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface SortIView {
    // 展示左侧数据
    void ShowLeftData(SortLeftBean sortLeftBean);

    // 展示右侧数据
    void ShowRightData(SortRightBean sortRightBean);
}
