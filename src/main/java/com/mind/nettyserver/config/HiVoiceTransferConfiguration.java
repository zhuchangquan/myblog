package com.mind.nettyserver.config;

import com.mind.nettyserver.handler.impl.ConfigCenterHandler;
import com.mind.nettyserver.thread.CatagoryThreadFactory;
import com.mind.nettyserver.thread.HwRejectExcutionHandler;
import com.mind.nettyserver.thread.SmartAssiatantThreadPoolExcutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Enzo Cotter on 2020/2/9.
 */
@Configuration
@Slf4j
public class HiVoiceTransferConfiguration {

    @Autowired
    ConfigCenterHandler configCenterHandler;

    @Autowired
    HwRejectExcutionHandler hwRejectExcutionHandler;


    @Bean
    public SmartAssiatantThreadPoolExcutor getAssistantThreadPoolExcutor() {
        int smartCoreSize = Integer.parseInt(configCenterHandler.getSmartCorePoolSize());
        int smartMaximumPoolSize = Integer.parseInt(configCenterHandler.getSmartMaximumPoolSize());
        long smartKeepAlive = Integer.parseInt(configCenterHandler.getSmartKeepAlive());
        int smartBlockingQueueSize = Integer.parseInt(configCenterHandler.getBlockingQueueSize());
        log.info("smartAssiatantThreadPoolExcutor 注入完成");
        return new SmartAssiatantThreadPoolExcutor(smartCoreSize,
                                                    smartMaximumPoolSize,
                                                    smartKeepAlive,
                                                    TimeUnit.SECONDS,
                                                     new ArrayBlockingQueue<>(smartBlockingQueueSize),
                                                    new CatagoryThreadFactory("hivoice-Transfer", "business"),
                                                    hwRejectExcutionHandler);
    }
}
