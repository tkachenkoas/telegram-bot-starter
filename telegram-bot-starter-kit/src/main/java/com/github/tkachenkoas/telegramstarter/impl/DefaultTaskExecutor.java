package com.github.tkachenkoas.telegramstarter.impl;

import com.github.tkachenkoas.telegramstarter.api.LongPollingUpdateTaskExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@ConditionalOnMissingBean(LongPollingUpdateTaskExecutor.class)
@Component
class DefaultTaskExecutor implements LongPollingUpdateTaskExecutor {

    private final Executor delegate = Executors.newSingleThreadExecutor();

    @Override
    public void execute(Runnable task) {
        delegate.execute(task);
    }
}
