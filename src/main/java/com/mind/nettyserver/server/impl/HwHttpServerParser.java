package com.mind.nettyserver.server.impl;

import com.mind.nettyserver.context.TerminalContext;
import com.mind.nettyserver.thread.GetRequestRunnable;
import com.mind.nettyserver.thread.SmartAssiatantThreadPoolExcutor;
import io.netty.handler.codec.http.HttpObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Enzo Cotter on 2020/2/21.
 */
@Component
public class HwHttpServerParser {

    @Autowired
    SmartAssiatantThreadPoolExcutor smartAssiatantThreadPoolExcutor;

    public void parseHeader(HttpObject msg, TerminalContext terminalContext) {
        smartAssiatantThreadPoolExcutor.execute(new GetRequestRunnable(terminalContext));
    }
}
