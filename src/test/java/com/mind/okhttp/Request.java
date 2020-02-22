package com.mind.okhttp;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/2/21.
 */
public class Request {
    @Getter
    private Map<String,String> headers;

    @Getter
    private String method;

    @Getter
    private HttpUrl url;

    Request(Builder builder) {
        //构造函数，省略属性赋值操作
        headers = builder.headers;
        method = builder.method;
        url = builder.url;
    }
    public Builder newBuilder() {//这里读者可以思考一下为什么会有一个newbuilder模式呢
        return new Builder(this);
    }
    //省略部分代码
    public static class Builder {
        HttpUrl url;
        String method;
        Map<String,String> headers = new HashMap<>();
        //省略部分代码
        Builder(Request request) {//这里读者可以想一想为什么在builder中会有这样一个构造方法，既然我们在创造builder的时候request是空的，那我们什么时候会用到这个方法呢？
            //构造函数，省略属性赋值操作
            url = request.url;
            method = request.method;
            headers = request.headers;
        }

        Builder() { this.method = "GET";}

        public Builder url(HttpUrl url) {
            this.url = url;
            return this;
        }

        public Builder addHeader(String name,String value) {
            this.headers.put(name,value);
            return this;
        }


        //省略部分代码
        public Request build() {
            if (url == null) throw new IllegalStateException("url == null");
            return new Request(this);
        }
    }
}
