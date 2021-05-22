package com.github.tkachenkoas.telegramstarter;

import org.springframework.core.task.TaskExecutor;

public interface LongPollingUpdateTaskExecutor extends TaskExecutor {

    @Override
    void execute(Runnable task);

}
