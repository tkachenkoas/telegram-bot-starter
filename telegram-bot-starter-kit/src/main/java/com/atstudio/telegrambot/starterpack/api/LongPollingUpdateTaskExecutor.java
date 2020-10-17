package com.atstudio.telegrambot.starterpack.api;

import org.springframework.core.task.TaskExecutor;

public interface LongPollingUpdateTaskExecutor extends TaskExecutor {

    @Override
    void execute(Runnable task);

}
