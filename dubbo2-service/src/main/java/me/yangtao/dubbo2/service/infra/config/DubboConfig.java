package me.yangtao.dubbo2.service.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@DubboComponentScan(basePackages = "me.yangtao")
@Slf4j
public class DubboConfig {

    @Value("${dubbo.protocol.port}")
    private int port;

    @Value("${dubbo.registry.address}")
    private String regAddress;

    @Value("${app.name}")
    private String appName;

    @Value("${dubbo.protocol.dispatcher: #{null}}")
    private String protocolDispatcher;

    @Value("${dubbo.protocol.threadpool: #{null}}")
    private String protocolThreadpool;

    @Value("${dubbo.protocol.threads: #{null}}")
    private Integer protocolThreads;

    @Value("${dubbo.protocol.queues: #{null}}")
    private Integer protocolQueues;

    @Value("${dubbo.qos.enable}")
    private boolean qosEnable;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
//        applicationConfig.setParameters(Maps.newHashMap());
//        applicationConfig.getParameters().put("dubbo_version", "2.7.15");
        applicationConfig.setQosEnable(qosEnable);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(regAddress);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(port);
        //默认配置
        protocolConfig.setTransporter("netty4");
        protocolConfig.setThreadpool("fixed");
        protocolConfig.setThreads(200);
        protocolConfig.setDispatcher("message");
        //读取配置文件
        if(StringUtils.isNotBlank(this.protocolDispatcher)){
            protocolConfig.setDispatcher(this.protocolDispatcher);
        }
        if(StringUtils.isNotBlank(this.protocolThreadpool)){
            protocolConfig.setThreadpool(this.protocolThreadpool);
        }
        if(this.protocolThreads != null && this.protocolThreads != 0){
            protocolConfig.setThreads(this.protocolThreads);
        }
        if(this.protocolQueues != null && this.protocolQueues != 0){
            protocolConfig.setQueues(this.protocolQueues);
        }
        log.info("====dubbo protocolConfig====");
        log.info("    dubbo port: " + protocolConfig.getPort());
        log.info("    dubbo transporter: " + protocolConfig.getTransporter());
        log.info("    dubbo threadpool: " + protocolConfig.getThreadpool());
        log.info("    dubbo threads: " + protocolConfig.getThreads());
        log.info("    dubbo dispatcher: " + protocolConfig.getDispatcher());
        log.info("    dubbo queues: " + protocolConfig.getQueues());
        return protocolConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setTimeout(5000);
        consumerConfig.setRetries(0);
        return consumerConfig;
    }
}
