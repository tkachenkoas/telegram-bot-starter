package com.github.tkachenkoas.telegramstarter;

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
