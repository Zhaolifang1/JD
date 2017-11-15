package com.example.jd.mine.view;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public interface MyIView {
    /**
     * 代码提供规范
     * 登录成功
     */
    void onLoginSuccess(String code);

    /**
     * 登录失败
     *
     * @param error
     */
    void onLoginFailed(String error);
}
