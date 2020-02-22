package com.mind.nettyserver.handler;

import com.mind.nettyserver.context.TerminalContext;

/**
 * Created by Enzo Cotter on 2020/2/10.
 */
public interface ProxyHttpSniff {

    void write(TerminalContext terminalContext);
}
