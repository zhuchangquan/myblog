package com.mind.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enzo Cotter on 2020/2/21.
 */
public class HttpCodec {
    ByteBuffer byteBuffer;

    public HttpCodec() {
        byteBuffer = ByteBuffer.allocate(10 * 1024);
    }


    public void writeRequest(OutputStream os, Request request) throws IOException {
        HttpUrl url = request.getUrl();
        StringBuffer sb = new StringBuffer();
        sb.append(request.getMethod());
        sb.append(" ");
        sb.append(url.getFile());
        sb.append(" ");
        sb.append("HTTP/1.1\r\n");

        Map<String, String> headers = request.getHeaders();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sb.append(entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
            sb.append("\r\n");
        }
        sb.append("\r\n");
        os.write(sb.toString().getBytes());
        System.out.println(sb.toString());
        os.flush();
        //System.out.println("send requestData success");
    }

    public String readLine(InputStream is) throws IOException {
        byte b;
        Boolean isMaybeEndOfLine = false;
        byteBuffer.clear();
        byteBuffer.mark();
    while ((b = (byte) is.read())!= -1) {
        byteBuffer.put(b);
            //\r
            if (b == 13) {
                isMaybeEndOfLine = true;
            } else if (isMaybeEndOfLine) {
                //\n
                if (b == 10) {
                    byte[] lineBytes = new byte[byteBuffer.position()];
                    byteBuffer.reset();
                    byteBuffer.get(lineBytes);
                    byteBuffer.clear();
                    byteBuffer.mark();
                    String line = new String(lineBytes);
                    return line;
                } else {
                    isMaybeEndOfLine = false;
                }
            }
        }
        return null;
    }

    public Map<String, String> readHeaders(InputStream is) throws IOException {
        HashMap<String,String> headers = new HashMap<>();
        while (true) {
            String line = readLine(is);

            if (line.equals("\r\n")) {
                break;
            }
            int index = line.indexOf(":");
            if (index > 0) {
                String name = line.substring(0, index);
                String value = line.substring(index + 2, line.length() - 2);
                headers.put(name, value);
            }


        }
        return headers;
    }

    public byte[] readBody(InputStream is, int len) throws IOException {
        byte[] bytes = new byte[len];
        int readNum = 0;
        while (true) {

            readNum = is.read(bytes);
            if (readNum == len) {
                return bytes;
            }
        }
    }

    public String readChunk(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        int len = -1;
        boolean isEmpttData = false;
        while (true) {
            if (len < 0) {
                String line = readLine(is);
                line.substring(0,line.length() - 2);
                len = Integer.valueOf(line, 16);
                isEmpttData = len == 0;
            }else {
                byte[] bytes = readBody(is, len + 2);
                sb.append(new String(bytes));
                len = -1;
                if (isEmpttData) {
                    return sb.toString();
                }
            }
        }



    }
}
