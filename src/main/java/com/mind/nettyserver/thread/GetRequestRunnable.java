package com.mind.nettyserver.thread;

import com.mind.nettyserver.context.TerminalContext;
import com.mind.nettyserver.handler.impl.GetRequestHandler;
import com.mind.nettyserver.utils.SpringContextUtil;

/**
 * Created by Enzo Cotter on 2020/2/19.
 */
public class GetRequestRunnable implements Runnable {
    GetRequestHandler getRequestHandler = (GetRequestHandler)SpringContextUtil.geBean(GetRequestHandler.class);

    TerminalContext terminalContext;

    public GetRequestRunnable (TerminalContext terminalContext) {
        this.terminalContext = terminalContext;
    }


    @Override
    public void run() {
        getRequestHandler.hand(terminalContext);
    }
}
