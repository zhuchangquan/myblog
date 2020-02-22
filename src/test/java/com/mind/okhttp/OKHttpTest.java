package com.mind.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.*;
import okhttp3.Request;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Enzo Cotter on 2020/2/21.
 */
public class OKHttpTest {
    //数据类型为json格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String URL = "http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";
    //创建okhttp对象
    private static OkHttpClient client = new OkHttpClient();



    private static ExecutorService executorService = Executors.newFixedThreadPool(2);


    public String post (String url, String json) {

        RequestBody requestBody = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(URL)
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            int code = response.code();
            String responseData = response.body().string();
            if (code == 200) {
                return responseData;
            } else {
                System.out.println("调用的接口返回错误的状态");
                return responseData;
            }
        }catch (IOException e) {
            System.out.println("出现IO异常");
        }
        return null;
    }

    public String post (String url, Map userheader, String json) {
        RequestBody requestBody = RequestBody.create(JSON,json);
        Headers headers = Headers.of(userheader);
        Request request = new Request.Builder().url(URL)
                .headers(headers)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            int code = response.code();
            String responseData = response.body().string();
            if (code == 200) {

                return responseData;
            } else {
                System.out.println("调用的接口返回错误的状态");
                return responseData;
            }
        }catch (IOException e) {
            System.out.println("出现IO异常");
        }
        return null;
    }

    public static String get(String url) {
        Request request = new Request.Builder().url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            int code = response.code();
            String responseData = response.body().string();
            if (code == 200) {
                return responseData;
            } else {
                System.out.println("调用的接口返回错误的状态");
                return responseData;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {

        executorService.execute( () -> {
            String response = get(URL);
            JSONObject object = JSONObject.parseObject(response);
            String pretty = com.alibaba.fastjson.JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
            System.out.println(pretty);
        });
        executorService.shutdown();


    }
}
