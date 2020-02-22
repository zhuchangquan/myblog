package com.mind.nettyserver.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Enzo Cotter on 2020/2/10.
 */
@Slf4j
@Component
public class HwRejectExcutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("ActiveCount:" + executor.getActiveCount());
    }
}
