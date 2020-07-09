package com.atstudio.telegrambot.starterpack.impl;

import com.atstudio.telegrambot.starterpack.api.LongPollingUpdateTaskExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.atstudio.telegrambot.starterpack.impl")
public class ImplBeansConfiguration {

    @ConditionalOnMissingBean(LongPollingUpdateTaskExecutor.class)
    @Bean
    public LongPollingUpdateTaskExecutor defaultExecutor() {
        return new DefaultTaskExecutor();
    }

}
