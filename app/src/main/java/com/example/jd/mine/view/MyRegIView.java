package com.example.jd.mine.view;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface MyRegIView {
    /**
     * 代码提供规范
     * 登录成功
     */
    void onRegSuccess(String code);

    /**
     * 登录失败
     *
     * @param error
     */
    void onRegFailed(String error);

}
