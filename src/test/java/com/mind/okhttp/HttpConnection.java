package com.mind.okhttp;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * Created by Enzo Cotter on 2020/2/21.
 */
public class HttpConnection {
    Request request;

    Socket socket;

    InputStream is;

    OutputStream os;

    public void setRequest(Request request) {
        this.request = request;
    }


    public InputStream call (HttpCodec httpCodec) throws IOException {
        createSocket();
        //发送请求
        httpCodec.writeRequest(os,request);
        //读取服务器响应
        return is;
    }

    private void createSocket() throws IOException{

        if (null != null){
            return ;
        }
        HttpUrl url = request.getUrl();
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            socket = SSLSocketFactory.getDefault().createSocket();
        } else {
            socket = new Socket();
        }
        socket.connect(new InetSocketAddress(url.getHost(),url.getPort()));
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }


}
