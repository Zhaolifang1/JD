package com.example.jd.home.sousuo;

import android.util.Log;

import com.example.jd.SouSuoBean;

import java.util.HashMap;
import java.util.List;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class Presaenter {

    private static final String TAG = "Presaenter";
    private IViewaa iv;
    public void attachView(IViewaa iv){
        this.iv=iv;
    }

    public void getNews(String a){

        HashMap<String, String > map = new HashMap<>();
        map.put("keywords", a);
        map.put("page", "1");
        map.put("source", "android");
//http://120.27.23.105/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=1&source=android
        HttpUtils.getInstance().get("http://120.27.23.105/product/searchProducts", map, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                SouSuoBean  bean= (SouSuoBean) o;
                if (bean!=null){
                    List<SouSuoBean.DataBean> data = bean.data;
                    iv.success(data);
                    Log.i(TAG,"AAAAAA"+data.size());
                }

            }

            @Override
            public void onFailed(Exception e) {

            }
        }, SouSuoBean.class);
    }

}
