package com.mind.nettyserver.handler.impl;

import com.mind.nettyserver.context.TerminalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Enzo Cotter on 2020/2/19.
 */
@Slf4j
@Component
public class GetRequestHandler {

    @Autowired
    ProxyHttpSniffImpl proxyHttpSniff;

    public void hand(TerminalContext terminalContext) {
        proxyHttpSniff.write(terminalContext);

    }
}
