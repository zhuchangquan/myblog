package com.mind.nettyserver.handler.impl;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Enzo Cotter on 2020/2/10.
 */
@Component
@Data
public class ConfigCenterHandler {

    @Value("${proxy.port}")
    private String port;

    @Value("${hivoice.smartCorePoolSize}")
    private String smartCorePoolSize;

    @Value("${hivoice.smartMaximumPoolSize}")
    private String smartMaximumPoolSize;

    @Value("${hivoice.smartKeepAlive}")
    private String smartKeepAlive;

    @Value("${hivoice.smartBlockingQueueSize}")
    public String blockingQueueSize;

}
