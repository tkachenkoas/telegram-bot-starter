package com.github.tkachenkoas.telegramstarter.impl;

import com.github.tkachenkoas.telegramstarter.api.LongPollingUpdateTaskExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.tkachenkoas.telegramstarter.impl")
public class ImplBeansConfiguration {

    @ConditionalOnMissingBean(LongPollingUpdateTaskExecutor.class)
    @Bean
    public LongPollingUpdateTaskExecutor defaultExecutor() {
        return new DefaultTaskExecutor();
    }

}
