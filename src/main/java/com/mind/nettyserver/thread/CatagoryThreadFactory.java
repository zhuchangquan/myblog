package com.mind.nettyserver.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Enzo Cotter on 2020/2/9.
 */
public class CatagoryThreadFactory implements ThreadFactory {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private String poolName;

    private String prefix;

    public CatagoryThreadFactory(String poolName, String prefix) {
        this.poolName = poolName;
        this.prefix = prefix;
    }


    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(prefix+"-"+ poolName + "-" + atomicInteger.getAndIncrement());
        thread.setUncaughtExceptionHandler(null);
        return thread;
    }
}
