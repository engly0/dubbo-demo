package me.yangtao.dubbo1.service;

import me.yangtao.dubbo1.service.infra.mysql.config.EnableWholeMysql;
import me.yangtao.dubbo1.service.infra.redis.config.EnableRedisCluster;
import me.yangtao.dubbo1.service.infra.rocketmq.config.EnableRocketMQProducer;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"me.yangtao"})
@DubboComponentScan(basePackages = {"me.yangtao"})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@EnableRedisCluster
@EnableRocketMQProducer
@EnableWholeMysql
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        try {
            SpringApplication.run(Main.class, args);
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage(), throwable);
            throw throwable;
        }
    }

}
