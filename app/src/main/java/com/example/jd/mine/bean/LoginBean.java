package com.example.jd.mine.bean;

import com.google.gson.Gson;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class LoginBean {

    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-15T13:26:28","email":null,"gender":0,"icon":null,"mobile":"18701295759","money":0,"nickname":null,"password":"123456","token":"C6F206D705DAFF283146C8A7AAC9B7D7","uid":1059,"username":"18701295759"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static LoginBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginBean.class);
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-15T13:26:28
         * email : null
         * gender : 0
         * icon : null
         * mobile : 18701295759
         * money : 0
         * nickname : null
         * password : 123456
         * token : C6F206D705DAFF283146C8A7AAC9B7D7
         * uid : 1059
         * username : 18701295759
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public int gender;
        public Object icon;
        public String mobile;
        public int money;
        public Object nickname;
        public String password;
        public String token;
        public int uid;
        public String username;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }
    }
}
