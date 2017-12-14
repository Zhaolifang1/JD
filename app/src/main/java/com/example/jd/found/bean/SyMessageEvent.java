package com.example.jd.found.bean;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class SyMessageEvent {
    String id;
    String title;


    public SyMessageEvent(String id, String title) {
        this.id = id;
        this.title = title;

    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
