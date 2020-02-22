package com.mind.nettyserver.server.impl;

import com.mind.nettyserver.handler.impl.ConfigCenterHandler;
import com.mind.nettyserver.server.NettyBootStrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HttpServerBootStrap implements NettyBootStrap {

    @Autowired
    ConfigCenterHandler configCenterHandler;



    @Override
    @PostConstruct
    public void start() {
        init();
    }

    private void init() {
        //定义两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //服务端启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new HwServerInitializer());
            String port = configCenterHandler.getPort();
            ChannelFuture channelFuture = serverBootstrap.bind(Integer.parseInt(configCenterHandler.getPort())).sync();
            Channel channel = channelFuture.channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class HwServerInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            pipeline.addLast("HttpServerCodec",new HttpServerCodec());
            pipeline.addLast("ProxyConnection", new ProxyConnection());
        }
    }
}
