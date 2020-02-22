package com.mind.okhttp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.io.InputStream;
import java.util.Map;

/**
 * 网络框架
 */
public class HttpTest {

    //数据类型为json格式
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //创建okhttp对象
    OkHttpClient client = new OkHttpClient();

    private static final String URL = "http://www.kuaidi100.com/query?type=yuantong&postid=11111111111";


    public static void main(String[] args) throws Exception{
        HttpUrl httpUrl = new HttpUrl(URL);
        Request request = new Request.Builder()
                .url(httpUrl)
                .addHeader("Connection", "keep-alive")
                .addHeader("Host",httpUrl.getHost())
                .build();
        HttpConnection connection = new HttpConnection();
        connection.setRequest(request);
        HttpCodec httpCodec = new HttpCodec();
        InputStream is = connection.call(httpCodec);

        //HTTP/1.1 200 OK
        String statusLine = httpCodec.readLine(is);
        System.out.println(statusLine);
        Map<String, String> headers = httpCodec.readHeaders(is);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        //解析响应体
        int contentLength = -1;
        if (headers.containsKey("Content-Length")) {
            contentLength = Integer.valueOf(headers.get("Content-Length"));
        }
        boolean isChunked = false;
        if (headers.containsKey("Transfer-Encoding")) {
            isChunked = headers.get("Transfer-Encoding").equalsIgnoreCase("chunked");

        }

        if (contentLength > 0) {
            //解析body
            byte[] bytes = httpCodec.readBody(is, contentLength);
            System.out.println("Response:" + new String(bytes));
        }else if (isChunked) {
            //分块解析body
            String s = httpCodec.readChunk(is);
            System.out.println("Response:" + s);

        }
    }
}
