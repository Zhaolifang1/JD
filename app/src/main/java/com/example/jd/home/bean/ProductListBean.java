package com.example.jd.home.bean;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class ProductListBean {
    private String proName;
    private int imgUrl;

    public ProductListBean(String proName, int imgUrl) {
        this.proName = proName;
        this.imgUrl = imgUrl;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
