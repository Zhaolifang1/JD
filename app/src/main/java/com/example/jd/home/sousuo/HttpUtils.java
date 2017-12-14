package com.example.jd.home.sousuo;

import android.os.Handler;
import android.text.TextUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 赵利芳
 *         类的作用：
 * @date
 */

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    private static Handler handler=new Handler();

    private static volatile HttpUtils instance;

    private HttpUtils(){

    }



    public static HttpUtils getInstance() {
        if (null == instance) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    public void get(String url, Map<String,String> map, final CallBack callBack, final Class cls ){
        // http://www.baoidu.com/login?mobile=11111&password=11111&age=1&name=zw

        // 1.http://www.baoidu.com/login                --------？ key=value&key=value
        // 2.http://www.baoidu.com/login?               --------- key=value&key=value
        // 3.http://www.baoidu.com/login?mobile=11111   -----&key=value&key=value


        if (TextUtils.isEmpty(url)){
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(url);

        if (url.contains("?")){
            //如果包含并且是最后一位，就是第二种类型
            if (url.indexOf("?")== url.length()-1){
            }else{
                //如果包含?  并且不是最后一位
                sb.append("&");
            }
        }else{
            //不包含？  对应的是第一种类型
            sb.append("?");
        }
        //遍历map集合进行拼接，拼接的形式是key=values
        for(Map.Entry<String,String> entry:map.entrySet()){
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        //如果存在&号，把最后一个&去掉
        if (sb.indexOf("&")!=-1){
            // lastIndexOf 最后一个
            sb.deleteCharAt(sb.lastIndexOf("&"));
        }



        OkHttpClient client = new OkHttpClient();
        Request build = new Request.Builder()
                .get()
                .url(sb.toString())
                .build();

        Call call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String reutl = response.body().string();
                //请求成功之后解析，通过自己的回调接口将数据传输回去
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Object o;
                        //如果是空的话
                        if (TextUtils.isEmpty(reutl)){
                            o=null;

                        }else{
                            o = GsonUtlis.getInstance().fromJson(reutl, cls);

                        }
                        callBack.onSuccess(o);
                    }
                });
            }
        });
    }

}