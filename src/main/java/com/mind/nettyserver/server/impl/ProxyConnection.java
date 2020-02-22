package com.mind.nettyserver.server.impl;

import com.mind.nettyserver.context.TerminalContext;
import com.mind.nettyserver.thread.SmartAssiatantThreadPoolExcutor;
import com.mind.nettyserver.utils.SpringContextUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by Enzo Cotter on 2020/2/9.
 */
@Slf4j
public class ProxyConnection extends SimpleChannelInboundHandler<HttpObject> {
    ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    HwHttpServerParser hwHttpServerParser = (HwHttpServerParser)SpringContextUtil.geBean(HwHttpServerParser.class);
    SmartAssiatantThreadPoolExcutor smartAssiatantThreadPoolExcutor = (SmartAssiatantThreadPoolExcutor) SpringContextUtil.geBean(SmartAssiatantThreadPoolExcutor.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        TerminalContext terminalContext = new TerminalContext();
        terminalContext.setChannel(ctx.channel());
        Channel channel = ctx.channel();
        if(msg instanceof HttpRequest){
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println("请求方法:" + httpRequest.method().name());
            //URL url = new URL(httpRequest.uri());
            //System.out.println("请求url:" + url.getPath());
            parseHttpHeader(msg, terminalContext);
        } else if (msg instanceof HttpContent) {
            parseHttpBody(msg);
        }
        channelGroup.forEach((ch) -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发送了消息");
            }else{
                channel.writeAndFlush("加入了聊天");
            }
        });

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel Active");
        super.channelActive(ctx);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    private void parseHttpHeader(HttpObject msg, TerminalContext terminalContext) {
        hwHttpServerParser.parseHeader(msg, terminalContext);
    }

    private void parseHttpBody(HttpObject msg) {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        channelGroup.add(ctx.channel());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");

        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        super.handlerRemoved(ctx);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}
