package com.mind.nettyserver.listener;

import com.mind.nettyserver.context.TerminalContext;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * Created by Enzo Cotter on 2020/2/19.
 */
public class HwResponseFutureListener implements GenericFutureListener {
    TerminalContext terminalContext;
    public HwResponseFutureListener (TerminalContext terminalContext) {
        this.terminalContext = terminalContext;
    }

    @Override
    public void operationComplete(Future future) throws Exception {
        if(future.isDone()) {
            Channel channel = terminalContext.getChannel();
            System.out.println( "GET of Response Success");
            channel.close();
        }
    }
}
