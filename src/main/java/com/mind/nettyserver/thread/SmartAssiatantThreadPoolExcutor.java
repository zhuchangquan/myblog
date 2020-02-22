package com.mind.nettyserver.thread;

import java.util.concurrent.*;

/**
 * Created by Enzo Cotter on 2020/2/9.
 */
public class SmartAssiatantThreadPoolExcutor extends ThreadPoolExecutor {
    private String prefix = "hivoice-Transfer";

    private String poolName = "business";

    private ThreadFactory threadFactory;


    public SmartAssiatantThreadPoolExcutor(int corePoolSize,
                                           int maximumPoolSize,
                                           long keepAliveTime,
                                           TimeUnit unit,
                                           BlockingQueue<Runnable> workQueue,
                                           ThreadFactory threadFactory,
                                           RejectedExecutionHandler rejectedExecutionHandler) {

        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, rejectedExecutionHandler);
    }
}
