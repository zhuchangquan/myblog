package com.mind.nettyserver.handler.impl;

import com.mind.nettyserver.context.TerminalContext;
import com.mind.nettyserver.handler.ProxyHttpSniff;
import com.mind.nettyserver.listener.HwResponseFutureListener;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

/**
 * Created by Enzo Cotter on 2020/2/10.
 */
@Component
public class ProxyHttpSniffImpl implements ProxyHttpSniff {


    @Override
    public void write(TerminalContext terminalContext) {
        Channel channel = terminalContext.getChannel();
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
        if (channel.isActive()){
            channel.writeAndFlush(response).addListener(new HwResponseFutureListener(terminalContext));
        } else {
            System.out.println("channel is Inactive");
        }
    }
}
