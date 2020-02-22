package com.mind.okhttp;

import lombok.Data;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Enzo Cotter on 2020/2/21.
 */
@Data
public class HttpUrl {
    String host;
    String protocol;
    int port;
    String file;

    public HttpUrl (String url) throws MalformedURLException {
        URL url1 = new URL(url);
        host = url1.getHost();
        file = url1.getFile();
        file = isEmpty(file) ? "/" : file;
        protocol = url1.getProtocol();
        port = url1.getPort();
        port = port == -1 ? url1.getDefaultPort() : port;

    }

    private boolean isEmpty(String file) {
        return file.isEmpty();
    }
}
