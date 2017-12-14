package com.example.jd.home.sousuo;

import com.google.gson.Gson;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class GsonUtlis {
    //Gson的单列模式
    private static Gson instance;
    private GsonUtlis(){

    }

    public static Gson getInstance(){
        if (instance==null){
            instance=new Gson();
        }
        return  instance;
    }
}